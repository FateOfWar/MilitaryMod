package org.valkyrienskies.military.model

import com.google.common.primitives.Ints
import me.alex_s168.math.Vec3
import me.alex_s168.meshlib.Triangle
import me.alex_s168.meshlib.texture.TextureCoordinate
import me.alex_s168.meshlib.texture.TextureFace
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import kotlin.math.ceil

private fun vertexToInts(
    x: Float,
    y: Float,
    z: Float,
    color: Int,
    texture: TextureAtlasSprite,
    u: Double,
    v: Double,
    lightmapvalue: Int = 0xffff,
    normal: Int
): IntArray = intArrayOf(
    java.lang.Float.floatToRawIntBits(x),
    java.lang.Float.floatToRawIntBits(y),
    java.lang.Float.floatToRawIntBits(z),
    color,
    java.lang.Float.floatToRawIntBits(texture.getU(u)),
    java.lang.Float.floatToRawIntBits(texture.getU(v)),
    lightmapvalue,
    normal
)

private fun normalToInt(n: Vec3): Int {
    val x = ((n.x * 127).toInt().toByte()).toInt() and 0xFF
    val y = ((n.y * 127).toInt().toByte()).toInt() and 0xFF
    val z = ((n.z * 127).toInt().toByte()).toInt() and 0xFF
    return x or (y shl 0x08) or (z shl 0x10);
}

fun Triangle.toMinecraftVertices(
    color: Int = 0xffffffff.toInt(),
    tex: TextureAtlasSprite,
    texCoords: TextureFace?
): IntArray {
    val n = normalToInt(normal)
    val tc = texCoords ?: TextureFace(
        TextureCoordinate(0f),
        TextureCoordinate(0f),
        TextureCoordinate(0f)
    )
    val (u0, v0) = tc.a
    val (u1, v1) = tc.b
    val (u2, v2) = tc.c
    val vd0 = vertexToInts(this.a.x, this.a.y, this.a.z, color, tex, ceil(u0.toDouble()), ceil(v0.toDouble()), normal = n)
    val vd1 = vertexToInts(this.b.x, this.b.y, this.b.z, color, tex, ceil(u1.toDouble()), ceil(v1.toDouble()), normal = n)
    val vd2 = vertexToInts(this.c.x, this.c.y, this.c.z, color, tex, ceil(u2.toDouble()), ceil(v2.toDouble()), normal = n)
    return Ints.concat(vd0, vd1, vd2, vd0)
}

fun TextureFace.toTri(): Triangle = Triangle(
    Vec3(),
    a,
    b,
    c
)