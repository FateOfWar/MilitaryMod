package org.valkyrienskies.military.model

import com.google.common.primitives.Ints
import me.alex_s168.math.Vec3
import me.alex_s168.meshlib.Triangle
import net.minecraft.client.renderer.texture.TextureAtlasSprite

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

private fun getUV(point: Vec3, a: Vec3, b: Vec3, c: Vec3): Pair<Float, Float> {
    val v0 = b - a
    val v1 = c - a
    val v2 = point - a
    val d00 = v0.dot(v0)
    val d01 = v0.dot(v1)
    val d11 = v1.dot(v1)
    val d20 = v2.dot(v0)
    val d21 = v2.dot(v1)
    val denom = d00 * d11 - d01 * d01
    val v = (d11 * d20 - d01 * d21) / denom
    val w = (d00 * d21 - d01 * d20) / denom
    val u = 1 - v - w
    return u to v
}

fun Triangle.toMinecraftVertices(
    color: Int = 0xffffffff.toInt(),
    tex: TextureAtlasSprite
): IntArray {
    val n = normalToInt(this.normal)
    val (u0, v0) = getUV(this.a, this.a, this.b, this.c)
    val (u1, v1) = getUV(this.b, this.a, this.b, this.c)
    val (u2, v2) = getUV(this.c, this.a, this.b, this.c)
    val vd0 = vertexToInts(this.a.x, this.a.y, this.a.z, color, tex, u0.toDouble(), v0.toDouble(), normal = n)
    val vd1 = vertexToInts(this.b.x, this.b.y, this.b.z, color, tex, u1.toDouble(), v1.toDouble(), normal = n)
    val vd2 = vertexToInts(this.c.x, this.c.y, this.c.z, color, tex, u2.toDouble(), v2.toDouble(), normal = n)
    return Ints.concat(vd0, vd1, vd2, vd0)
}