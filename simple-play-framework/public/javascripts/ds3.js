function des(b, d, c, e, g, f) {
    var h = [16843776, 0, 65536, 16843780, 16842756, 66564, 4, 65536, 1024, 16843776, 16843780, 1024, 16778244, 16842756, 16777216, 4, 1028, 16778240, 16778240, 66560, 66560, 16842752, 16842752, 16778244, 65540, 16777220, 16777220, 65540, 0, 1028, 66564, 16777216, 65536, 16843780, 4, 16842752, 16843776, 16777216, 16777216, 1024, 16842756, 65536, 66560, 16777220, 1024, 4, 16778244, 66564, 16843780, 65540, 16842752, 16778244, 16777220, 1028, 66564, 16843776, 1028, 16778240, 16778240, 0, 65540, 66560, 0, 16842756],
        l = [-2146402272, -2147450880,
            32768, 1081376, 1048576, 32, -2146435040, -2147450848, -2147483616, -2146402272, -2146402304, -2147483648, -2147450880, 1048576, 32, -2146435040, 1081344, 1048608, -2147450848, 0, -2147483648, 32768, 1081376, -2146435072, 1048608, -2147483616, 0, 1081344, 32800, -2146402304, -2146435072, 32800, 0, 1081376, -2146435040, 1048576, -2147450848, -2146435072, -2146402304, 32768, -2146435072, -2147450880, 32, -2146402272, 1081376, 32, 32768, -2147483648, 32800, -2146402304, 1048576, -2147483616, 1048608, -2147450848, -2147483616, 1048608, 1081344, 0, -2147450880,
            32800, -2147483648, -2146435040, -2146402272, 1081344],
        k = [520, 134349312, 0, 134348808, 134218240, 0, 131592, 134218240, 131080, 134217736, 134217736, 131072, 134349320, 131080, 134348800, 520, 134217728, 8, 134349312, 512, 131584, 134348800, 134348808, 131592, 134218248, 131584, 131072, 134218248, 8, 134349320, 512, 134217728, 134349312, 134217728, 131080, 520, 131072, 134349312, 134218240, 0, 512, 131080, 134349320, 134218240, 134217736, 512, 0, 134348808, 134218248, 131072, 134217728, 134349320, 8, 131592, 131584, 134217736, 134348800, 134218248, 520, 134348800,
            131592, 8, 134348808, 131584],
        q = [8396801, 8321, 8321, 128, 8396928, 8388737, 8388609, 8193, 0, 8396800, 8396800, 8396929, 129, 0, 8388736, 8388609, 1, 8192, 8388608, 8396801, 128, 8388608, 8193, 8320, 8388737, 1, 8320, 8388736, 8192, 8396928, 8396929, 129, 8388736, 8388609, 8396800, 8396929, 129, 0, 0, 8396800, 8320, 8388736, 8388737, 1, 8396801, 8321, 8321, 128, 8396929, 129, 1, 8192, 8388609, 8193, 8396928, 8388737, 8193, 8320, 8388608, 8396801, 128, 8388608, 8192, 8396928],
        u = [256, 34078976, 34078720, 1107296512, 524288, 256, 1073741824, 34078720, 1074266368, 524288, 33554688,
            1074266368, 1107296512, 1107820544, 524544, 1073741824, 33554432, 1074266112, 1074266112, 0, 1073742080, 1107820800, 1107820800, 33554688, 1107820544, 1073742080, 0, 1107296256, 34078976, 33554432, 1107296256, 524544, 524288, 1107296512, 256, 33554432, 1073741824, 34078720, 1107296512, 1074266368, 33554688, 1073741824, 1107820544, 34078976, 1074266368, 256, 33554432, 1107820544, 1107820800, 524544, 1107296256, 1107820800, 34078720, 0, 1074266112, 1107296256, 524544, 33554688, 1073742080, 524288, 0, 1074266112, 34078976, 1073742080],
        r = [536870928, 541065216,
            16384, 541081616, 541065216, 16, 541081616, 4194304, 536887296, 4210704, 4194304, 536870928, 4194320, 536887296, 536870912, 16400, 0, 4194320, 536887312, 16384, 4210688, 536887312, 16, 541065232, 541065232, 0, 4210704, 541081600, 16400, 4210688, 541081600, 536870912, 536887296, 16, 541065232, 4210688, 541081616, 4194304, 16400, 536870928, 4194304, 536887296, 536870912, 16400, 536870928, 541081616, 4210688, 541065216, 4210704, 541081600, 0, 541065232, 16, 16384, 541065216, 4210704, 16384, 4194320, 536887312, 0, 541081600, 536870912, 4194320, 536887312],
        t = [2097152,
            69206018, 67110914, 0, 2048, 67110914, 2099202, 69208064, 69208066, 2097152, 0, 67108866, 2, 67108864, 69206018, 2050, 67110912, 2099202, 2097154, 67110912, 67108866, 69206016, 69208064, 2097154, 69206016, 2048, 2050, 69208066, 2099200, 2, 67108864, 2099200, 67108864, 2099200, 2097152, 67110914, 67110914, 69206018, 69206018, 2, 2097154, 67108864, 67110912, 2097152, 69208064, 2050, 2099202, 69208064, 2050, 67108866, 69208066, 69206016, 2099200, 0, 2, 69208066, 0, 2099202, 69206016, 2048, 67108866, 67110912, 2048, 2097154],
        s = [268439616, 4096, 262144, 268701760, 268435456,
            268439616, 64, 268435456, 262208, 268697600, 268701760, 266240, 268701696, 266304, 4096, 64, 268697600, 268435520, 268439552, 4160, 266240, 262208, 268697664, 268701696, 4160, 0, 0, 268697664, 268435520, 268439552, 266304, 262144, 266304, 262144, 268701696, 4096, 64, 268697664, 4096, 266304, 268439552, 64, 268435520, 268697600, 268697664, 268435456, 262144, 268439616, 0, 268701760, 262208, 268435520, 268697600, 268439552, 268439616, 0, 268701760, 266240, 266240, 4160, 4160, 262208, 268435456, 268701696];
    b = des_createKeys(b);
    var p = 0, v, w, x, n, m, A, y, D, z, E, F,
        G, B = d.length, C = 0, H = 32 == b.length ? 3 : 9;
    A = 3 == H ? c ? [0, 32, 2] : [30, -2, -2] : c ? [0, 32, 2, 62, 30, -2, 64, 96, 2] : [94, 62, -2, 32, 64, 2, 30, -2, -2];
    2 == f ? d += "        " : 1 == f ? (f = 8 - B % 8, d += String.fromCharCode(f, f, f, f, f, f, f, f), 8 == f && (B += 8)) : f || (d += "\x00\x00\x00\x00\x00\x00\x00\x00");
    tempresult = result = "";
    1 == e && (y = g.charCodeAt(p++) << 24 | g.charCodeAt(p++) << 16 | g.charCodeAt(p++) << 8 | g.charCodeAt(p++), z = g.charCodeAt(p++) << 24 | g.charCodeAt(p++) << 16 | g.charCodeAt(p++) << 8 | g.charCodeAt(p++), p = 0);
    for (; p < B;) {
        n = d.charCodeAt(p++) << 24 | d.charCodeAt(p++) <<
            16 | d.charCodeAt(p++) << 8 | d.charCodeAt(p++);
        m = d.charCodeAt(p++) << 24 | d.charCodeAt(p++) << 16 | d.charCodeAt(p++) << 8 | d.charCodeAt(p++);
        1 == e && (c ? (n ^= y, m ^= z) : (D = y, E = z, y = n, z = m));
        f = (n >>> 4 ^ m) & 252645135;
        m ^= f;
        n ^= f << 4;
        f = (n >>> 16 ^ m) & 65535;
        m ^= f;
        n ^= f << 16;
        f = (m >>> 2 ^ n) & 858993459;
        n ^= f;
        m ^= f << 2;
        f = (m >>> 8 ^ n) & 16711935;
        n ^= f;
        m ^= f << 8;
        f = (n >>> 1 ^ m) & 1431655765;
        m ^= f;
        n ^= f << 1;
        n = n << 1 | n >>> 31;
        m = m << 1 | m >>> 31;
        for (v = 0; v < H; v += 3) {
            F = A[v + 1];
            G = A[v + 2];
            for (g = A[v]; g != F; g += G) w = m ^ b[g], x = (m >>> 4 | m << 28) ^ b[g + 1], f = n, n = m, m = f ^ (l[w >>> 24 & 63] | q[w >>> 16 & 63] | r[w >>>
            8 & 63] | s[w & 63] | h[x >>> 24 & 63] | k[x >>> 16 & 63] | u[x >>> 8 & 63] | t[x & 63]);
            f = n;
            n = m;
            m = f
        }
        n = n >>> 1 | n << 31;
        m = m >>> 1 | m << 31;
        f = (n >>> 1 ^ m) & 1431655765;
        m ^= f;
        n ^= f << 1;
        f = (m >>> 8 ^ n) & 16711935;
        n ^= f;
        m ^= f << 8;
        f = (m >>> 2 ^ n) & 858993459;
        n ^= f;
        m ^= f << 2;
        f = (n >>> 16 ^ m) & 65535;
        m ^= f;
        n ^= f << 16;
        f = (n >>> 4 ^ m) & 252645135;
        m ^= f;
        n ^= f << 4;
        1 == e && (c ? (y = n, z = m) : (n ^= D, m ^= E));
        tempresult += String.fromCharCode(n >>> 24, n >>> 16 & 255, n >>> 8 & 255, n & 255, m >>> 24, m >>> 16 & 255, m >>> 8 & 255, m & 255);
        C += 8;
        512 == C && (result += tempresult, tempresult = "", C = 0)
    }
    return result + tempresult
}

function des_createKeys(b) {
    pc2bytes0 = [0, 4, 536870912, 536870916, 65536, 65540, 536936448, 536936452, 512, 516, 536871424, 536871428, 66048, 66052, 536936960, 536936964];
    pc2bytes1 = [0, 1, 1048576, 1048577, 67108864, 67108865, 68157440, 68157441, 256, 257, 1048832, 1048833, 67109120, 67109121, 68157696, 68157697];
    pc2bytes2 = [0, 8, 2048, 2056, 16777216, 16777224, 16779264, 16779272, 0, 8, 2048, 2056, 16777216, 16777224, 16779264, 16779272];
    pc2bytes3 = [0, 2097152, 134217728, 136314880, 8192, 2105344, 134225920, 136323072, 131072, 2228224, 134348800, 136445952,
        139264, 2236416, 134356992, 136454144];
    pc2bytes4 = [0, 262144, 16, 262160, 0, 262144, 16, 262160, 4096, 266240, 4112, 266256, 4096, 266240, 4112, 266256];
    pc2bytes5 = [0, 1024, 32, 1056, 0, 1024, 32, 1056, 33554432, 33555456, 33554464, 33555488, 33554432, 33555456, 33554464, 33555488];
    pc2bytes6 = [0, 268435456, 524288, 268959744, 2, 268435458, 524290, 268959746, 0, 268435456, 524288, 268959744, 2, 268435458, 524290, 268959746];
    pc2bytes7 = [0, 65536, 2048, 67584, 536870912, 536936448, 536872960, 536938496, 131072, 196608, 133120, 198656, 537001984, 537067520, 537004032,
        537069568];
    pc2bytes8 = [0, 262144, 0, 262144, 2, 262146, 2, 262146, 33554432, 33816576, 33554432, 33816576, 33554434, 33816578, 33554434, 33816578];
    pc2bytes9 = [0, 268435456, 8, 268435464, 0, 268435456, 8, 268435464, 1024, 268436480, 1032, 268436488, 1024, 268436480, 1032, 268436488];
    pc2bytes10 = [0, 32, 0, 32, 1048576, 1048608, 1048576, 1048608, 8192, 8224, 8192, 8224, 1056768, 1056800, 1056768, 1056800];
    pc2bytes11 = [0, 16777216, 512, 16777728, 2097152, 18874368, 2097664, 18874880, 67108864, 83886080, 67109376, 83886592, 69206016, 85983232, 69206528, 85983744];
    pc2bytes12 = [0, 4096, 134217728, 134221824, 524288, 528384, 134742016, 134746112, 16, 4112, 134217744, 134221840, 524304, 528400, 134742032, 134746128];
    pc2bytes13 = [0, 4, 256, 260, 0, 4, 256, 260, 1, 5, 257, 261, 1, 5, 257, 261];
    for (var d = 8 < b.length ? 3 : 1, c = Array(32 * d), e = [0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0], g, f, h = 0, l = 0, k, q = 0; q < d; q++) for (left = b.charCodeAt(h++) << 24 | b.charCodeAt(h++) << 16 | b.charCodeAt(h++) << 8 | b.charCodeAt(h++), right = b.charCodeAt(h++) << 24 | b.charCodeAt(h++) << 16 | b.charCodeAt(h++) << 8 | b.charCodeAt(h++), k = (left >>> 4 ^ right) & 252645135,
                                                                                                                                                                   right ^= k, left ^= k << 4, k = (right >>> -16 ^ left) & 65535, left ^= k, right ^= k << -16, k = (left >>> 2 ^ right) & 858993459, right ^= k, left ^= k << 2, k = (right >>> -16 ^ left) & 65535, left ^= k, right ^= k << -16, k = (left >>> 1 ^ right) & 1431655765, right ^= k, left ^= k << 1, k = (right >>> 8 ^ left) & 16711935, left ^= k, right ^= k << 8, k = (left >>> 1 ^ right) & 1431655765, right ^= k, left ^= k << 1, k = left << 8 | right >>> 20 & 240, left = right << 24 | right << 8 & 16711680 | right >>> 8 & 65280 | right >>> 24 & 240, right = k, i = 0; i < e.length; i++) e[i] ? (left = left << 2 | left >>> 26, right = right << 2 | right >>> 26) : (left = left << 1 |
        left >>> 27, right = right << 1 | right >>> 27), left &= -15, right &= -15, g = pc2bytes0[left >>> 28] | pc2bytes1[left >>> 24 & 15] | pc2bytes2[left >>> 20 & 15] | pc2bytes3[left >>> 16 & 15] | pc2bytes4[left >>> 12 & 15] | pc2bytes5[left >>> 8 & 15] | pc2bytes6[left >>> 4 & 15], f = pc2bytes7[right >>> 28] | pc2bytes8[right >>> 24 & 15] | pc2bytes9[right >>> 20 & 15] | pc2bytes10[right >>> 16 & 15] | pc2bytes11[right >>> 12 & 15] | pc2bytes12[right >>> 8 & 15] | pc2bytes13[right >>> 4 & 15], k = (f >>> 16 ^ g) & 65535, c[l++] = g ^ k, c[l++] = f ^ k << 16;
    return c
}

var Is2048 = false;

function stringToHex(b) {
    for (var d = "", c = "0123456789abcdef".split(""), e = 0; e < b.length; e++) d += c[b.charCodeAt(e) >> 4] + c[b.charCodeAt(e) & 15];
    return d
}

function encryptSetPwdNoVerifyRSABlock256(b, d, c, e, g) {
    c = doSHA256Hash(Util.getByteArray(c + e));
    e = Array(16);
    for (var f = 0; 16 > f; f++) e[f] = c[f] ^ c[f + 16];
    c = new BigInteger(Util.toHexString(e), 16);
    g = new BigInteger(g, 16);
    c = c.xor(g);
    g = "0210" + c.toString(16);
    g = new BigInteger(g, 16);
    c = new RSAKey;
    c.setPublic(d, b);
    if (d.length == 512) {
        Is2048 = true
    }
    return c.encrypt(g).toString(16)
}

function encryptSetPwdNoVerifyNoUserRSABlock256(b, d, e, g) {
    c = doSHA256Hash(Util.getByteArray(e));
    e = Array(16);
    for (var f = 0; 16 > f; f++) e[f] = c[f] ^ c[f + 16];
    c = new BigInteger(Util.toHexString(e), 16);
    g = new BigInteger(g, 16);
    c = c.xor(g);
    g = "0210" + c.toString(16);
    g = new BigInteger(g, 16);
    c = new RSAKey;
    c.setPublic(d, b);
    if (d.length == 512) {
        Is2048 = true
    }
    return c.encrypt(g).toString(16)
}

function encryptSetPwdNoVerifyRSABlockMD5(b, d, c, e, g) {
    c = MD5(c + e);
    c = new BigInteger(c, 16);
    g = new BigInteger(g, 16);
    c = c.xor(g);
    g = "0210" + c.toString(16);
    g = new BigInteger(g, 16);
    c = new RSAKey;
    c.setPublic(d, b);
    if (d.length == 512) {
        Is2048 = true
    }
    return c.encrypt(g).toString(16)
}

function encryptVerifyStaticRSABlock256(b, d, c, e, g) {
    c = doSHA256Hash(Util.getByteArray(c + e));
    e = Array(16);
    for (var f = 0; 16 > f; f++) e[f] = c[f] ^ c[f + 16];
    c = new BigInteger(Util.toHexString(e), 16);
    g = new BigInteger(g, 16);
    c = c.xor(g);
    g = new BigInteger("0110" + c.toString(16), 16);
    c = new RSAKey;
    c.setPublic(d, b);
    if (d.length == 512) {
        Is2048 = true
    }
    return c.encrypt(g).toString(16)
}

function encryptVerifyStaticNoUserRSABlock256(b, d, e, g) {
    c = doSHA256Hash(Util.getByteArray(e));
    e = Array(16);
    for (var f = 0; 16 > f; f++) e[f] = c[f] ^ c[f + 16];
    c = new BigInteger(Util.toHexString(e), 16);
    g = new BigInteger(g, 16);
    c = c.xor(g);
    g = new BigInteger("0110" + c.toString(16), 16);
    c = new RSAKey;
    c.setPublic(d, b);
    if (d.length == 512) {
        Is2048 = true
    }
    return c.encrypt(g).toString(16)
}

function encryptVerifyStaticRSABlockMD5(b, d, c, e, g) {
    c = MD5(c + e);
    c = new BigInteger(c, 16);
    g = new BigInteger(g, 16);
    c = c.xor(g);
    g = new BigInteger("0110" + c.toString(16), 16);
    c = new RSAKey;
    c.setPublic(d, b);
    if (d.length == 512) {
        Is2048 = true
    }
    return c.encrypt(g).toString(16)
}

function encryptSetPwdRSABlock256(b, d, c, e, g, f) {
    c = doSHA256Hash(Util.getByteArray(c + e));
    e = Array(16);
    for (var h = 0; 16 > h; h++) e[h] = c[h] ^ c[h + 16];
    c = new BigInteger(Util.toHexString(e), 16);
    f = new BigInteger(f, 16);
    c = c.xor(f);
    f = "0210" + c.toString(16);
    c = g.length.toString(16);
    16 > g.length && (c = "0" + c);
    g = new BigInteger(getByteArray(g));
    g = "03" + c + g.toString(16);
    g = new BigInteger(f + g, 16);
    f = new RSAKey;
    f.setPublic(d, b);
    return f.encrypt(g).toString(16)
}

function encryptVerifyRSABlock256(b, d, c, e, g, f) {
    c = doSHA256Hash(Util.getByteArray(c + e));
    e = Array(16);
    for (var h = 0; 16 > h; h++) e[h] = c[h] ^ c[h + 16];
    c = new BigInteger(Util.toHexString(e), 16);
    f = new BigInteger(f, 16);
    c = c.xor(f);
    f = g.length.toString(16);
    16 > g.length && (f = "0" + f);
    g = new BigInteger(getByteArray(g));
    g = "03" + f + g.toString(16);
    biTLVOTIP = new BigInteger(g, 16);
    f = c.toString(16) + biTLVOTIP.toString(16);
    g = new BigInteger("0110" + f, 16);
    f = new RSAKey;
    f.setPublic(d, b);
    return f.encrypt(g).toString(16)
}

function encryptChangePwdNoVerifyRSABlock256(b, d, c, e, g, f, h) {
    e = doSHA256Hash(Util.getByteArray(c + e));
    for (var l = Array(16), k = 0; 16 > k; k++) l[k] = e[k] ^ e[k + 16];
    e = new BigInteger(Util.toHexString(l), 16);
    l = new BigInteger(f, 16);
    e = e.xor(l);
    f = "0110" + e.toString(16);
    e = doSHA256Hash(Util.getByteArray(c + g));
    l = Array(16);
    for (k = 0; 16 > k; k++) l[k] = e[k] ^ e[k + 16];
    e = new BigInteger(Util.toHexString(l), 16);
    l = new BigInteger(h, 16);
    e = e.xor(l);
    c = "0210" + e.toString(16);
    c = new BigInteger(f + c, 16);
    g = new RSAKey;
    g.setPublic(d, b);
    if (d.length == 512) {
        Is2048 = true
    }
    return g.encrypt(c).toString(16)
}

function encryptChangePwdNoVerifyNoUserRSABlock256(b, d, e, g, f, h) {
    e = doSHA256Hash(Util.getByteArray(e));
    for (var l = Array(16), k = 0; 16 > k; k++) l[k] = e[k] ^ e[k + 16];
    e = new BigInteger(Util.toHexString(l), 16);
    l = new BigInteger(f, 16);
    e = e.xor(l);
    f = "0110" + e.toString(16);
    e = doSHA256Hash(Util.getByteArray(g));
    l = Array(16);
    for (k = 0; 16 > k; k++) l[k] = e[k] ^ e[k + 16];
    e = new BigInteger(Util.toHexString(l), 16);
    l = new BigInteger(h, 16);
    e = e.xor(l);
    c = "0210" + e.toString(16);
    c = new BigInteger(f + c, 16);
    g = new RSAKey;
    g.setPublic(d, b);
    if (d.length == 512) {
        Is2048 = true
    }
    return g.encrypt(c).toString(16)
}

function encryptChangePwdNoVerifyRSABlockMD5(b, d, c, e, g, f, h) {
    e = MD5(c + e);
    e = new BigInteger(e, 16);
    l = new BigInteger(f, 16);
    e = e.xor(l);
    f = "0110" + e.toString(16);
    e = MD5(c + g);
    e = new BigInteger(e, 16);
    l = new BigInteger(h, 16);
    e = e.xor(l);
    c = "0210" + e.toString(16);
    c = new BigInteger(f + c, 16);
    g = new RSAKey;
    g.setPublic(d, b);
    if (d.length == 512) {
        Is2048 = true
    }
    return g.encrypt(c).toString(16)
}

function encryptChangePwdRSABlock256(b, d, c, e, g, f, h, l) {
    e = doSHA256Hash(Util.getByteArray(c + e));
    for (var k = Array(16), q = 0; 16 > q; q++) k[q] = e[q] ^ e[q + 16];
    e = new BigInteger(Util.toHexString(k), 16);
    k = new BigInteger(h, 16);
    e = e.xor(k);
    h = "0110" + e.toString(16);
    e = doSHA256Hash(Util.getByteArray(c + g));
    k = Array(16);
    for (q = 0; 16 > q; q++) k[q] = e[q] ^ e[q + 16];
    e = new BigInteger(Util.toHexString(k), 16);
    k = new BigInteger(l, 16);
    e = e.xor(k);
    c = "0210" + e.toString(16);
    g = f.length.toString(16);
    16 > f.length && (g = "0" + g);
    f = new BigInteger(getByteArray(f));
    f = "03" + g + f.toString(16);
    f = new BigInteger(h + c + f, 16);
    h = new RSAKey;
    h.setPublic(d, b);
    return h.encrypt(f).toString(16)
}

function encryptVerifyOtipRSABlock(b, d, c) {
    var e = c.length.toString(16);
    16 > c.length && (e = "0" + e);
    c = new BigInteger(getByteArray(c));
    e = "03" + e + c.toString(16);
    biTLVOTIP = new BigInteger(e, 16);
    e = new RSAKey;
    e.setPublic(d, b);
    return e.encrypt(biTLVOTIP).toString(16)
}

function doSHA256Hash(b) {
    return Util.fromHexString(sha256Hash(Util.cByteArrayToNString(b)))
}

function rsaDES3EncryptDataPKCS5Padding_CBC(b, d, c, e) {
    var g = des3KeyGen(), f = new RSAKey;
    f.setPublic(d, b);
    b = f.encryptS(new BigInteger(g, 16));
    c = des3EncryptPKCS5Padding_CBC(g, c, e);
    return b.toString(16) + c
}

function des3EncryptPKCS5Padding_CBC(b, d, c) {
    b = Util.fromHexToString(b);
    c = Util.fromHexToString(c);
    return Util.stringToHex(des(b, d, 1, 1, c, 1))
}

function des3KeyGen() {
    return Util.toHexString(Util.randomBytes(24))
}

function getByteArray(b) {
    var a = [];
    for (var d = 0; d < b.length; d++) a[d] = b.charCodeAt(d);
    return a
}

var dbits, j_lm = !0;

function BigInteger(b, d, c) {
    null != b && ("number" == typeof b ? this.fromNumber(b, d, c) : null == d && "string" != typeof b ? this.fromString(b, 256) : this.fromString(b, d))
}

function nbi() {
    return new BigInteger(null)
}

function am1(b, d, c, e, g, f) {
    for (; 0 <= --f;) {
        var h = d * this[b++] + c[e] + g;
        g = Math.floor(h / 67108864);
        c[e++] = h & 67108863
    }
    return g
}

var MD5 = function (T) {
    function O(G) {
        G = G.replace(/\r\n/g, "\n");
        var F = "", I, H;
        for (H = 0; H < G.length; H++) {
            I = G.charCodeAt(H);
            if (I < 128) {
                F += String.fromCharCode(I)
            } else {
                if ((I > 127) && (I < 2048)) {
                    F += String.fromCharCode((I >> 6) | 192);
                    F += String.fromCharCode((I & 63) | 128)
                } else {
                    F += String.fromCharCode((I >> 12) | 224);
                    F += String.fromCharCode(((I >> 6) & 63) | 128);
                    F += String.fromCharCode((I & 63) | 128)
                }
            }
        }
        return F
    }

    function L(c) {
        var b = new Array(), a, I = c.length, Z, H, G, F;
        for (a = 0; a <= 13; a++) {
            a * 4 <= I ? (a * 4 == I ? Z = 128 : Z = c.charCodeAt(a * 4)) : Z = 0;
            a * 4 + 1 <= I ? (a * 4 + 1 == I ? H = 128 : H = c.charCodeAt(a * 4 + 1)) : H = 0;
            a * 4 + 2 <= I ? (a * 4 + 2 == I ? G = 128 : G = c.charCodeAt(a * 4 + 2)) : G = 0;
            a * 4 + 3 <= I ? (a * 4 + 3 == I ? F = 128 : F = c.charCodeAt(a * 4 + 3)) : F = 0;
            b[a] = new dW((F & 255) << 8 | G & 255, (H & 255) << 8 | Z & 255)
        }
        b[14] = new dW(0, (I * 8) & 65535);
        b[15] = new dW(0, 0);
        return b
    }

    function N(F, H, G) {
        return (F.and(H)).or((F.not()).and(G))
    }

    function M(F, H, G) {
        return (F.and(G)).or(H.and(G.not()))
    }

    function K(F, H, G) {
        return (F.xor(H)).xor(G)
    }

    function J(F, H, G) {
        return H.xor(F.or(G.not()))
    }

    function C(I, G, g, f, F, e, Z, H) {
        I = (((N(G, g, f).aU(F)).aU(new dW(Z, H))).aU(I));
        return G.aU(I.clf(e))
    }

    function P(I, G, g, f, F, e, Z, H) {
        I = (((M(G, g, f).aU(F)).aU(new dW(Z, H))).aU(I));
        return G.aU(I.clf(e))
    }

    function Y(I, G, g, f, F, e, Z, H) {
        I = (((K(G, g, f).aU(F)).aU(new dW(Z, H))).aU(I));
        return G.aU(I.clf(e))
    }

    function B(I, G, g, f, F, e, Z, H) {
        I = (((J(G, g, f).aU(F)).aU(new dW(Z, H))).aU(I));
        return G.aU(I.clf(e))
    }

    var X = new dW(26437, 8961), W = new dW(61389, 43913), V = new dW(39098, 56574), U = new dW(4146, 21622), R, S, A, D, Q, E = Array();
    E = L(O(T));
    for (R = 0; R < E.length; R += 16) {
        S = X;
        A = W;
        D = V;
        Q = U;
        X = C(X, W, V, U, E[R + 0], 7, 55146, 42104);
        U = C(U, X, W, V, E[R + 1], 12, 59591, 46934);
        V = C(V, U, X, W, E[R + 2], 17, 9248, 28891);
        W = C(W, V, U, X, E[R + 3], 22, 49597, 52974);
        X = C(X, W, V, U, E[R + 4], 7, 62844, 4015);
        U = C(U, X, W, V, E[R + 5], 12, 18311, 50730);
        V = C(V, U, X, W, E[R + 6], 17, 43056, 17939);
        W = C(W, V, U, X, E[R + 7], 22, 64838, 38145);
        X = C(X, W, V, U, E[R + 8], 7, 27008, 39128);
        U = C(U, X, W, V, E[R + 9], 12, 35652, 63407);
        V = C(V, U, X, W, E[R + 10], 17, 65535, 23473);
        W = C(W, V, U, X, E[R + 11], 22, 35164, 55230);
        X = C(X, W, V, U, E[R + 12], 7, 27536, 4386);
        U = C(U, X, W, V, E[R + 13], 12, 64920, 29075);
        V = C(V, U, X, W, E[R + 14], 17, 42617, 17294);
        W = C(W, V, U, X, E[R + 15], 22, 18868, 2081);
        X = P(X, W, V, U, E[R + 1], 5, 63006, 9570);
        U = P(U, X, W, V, E[R + 6], 9, 49216, 45888);
        V = P(V, U, X, W, E[R + 11], 14, 9822, 23121);
        W = P(W, V, U, X, E[R + 0], 20, 59830, 51114);
        X = P(X, W, V, U, E[R + 5], 5, 54831, 4189);
        U = P(U, X, W, V, E[R + 10], 9, 580, 5203);
        V = P(V, U, X, W, E[R + 15], 14, 55457, 59009);
        W = P(W, V, U, X, E[R + 4], 20, 59347, 64456);
        X = P(X, W, V, U, E[R + 9], 5, 8673, 52710);
        U = P(U, X, W, V, E[R + 14], 9, 49975, 2006);
        V = P(V, U, X, W, E[R + 3], 14, 62677, 3463);
        W = P(W, V, U, X, E[R + 8], 20, 17754, 5357);
        X = P(X, W, V, U, E[R + 13], 5, 43491, 59653);
        U = P(U, X, W, V, E[R + 2], 9, 64751, 41976);
        V = P(V, U, X, W, E[R + 7], 14, 26479, 729);
        W = P(W, V, U, X, E[R + 12], 20, 36138, 19594);
        X = Y(X, W, V, U, E[R + 5], 4, 65530, 14658);
        U = Y(U, X, W, V, E[R + 8], 11, 34673, 63105);
        V = Y(V, U, X, W, E[R + 11], 16, 28061, 24866);
        W = Y(W, V, U, X, E[R + 14], 23, 64997, 14348);
        X = Y(X, W, V, U, E[R + 1], 4, 42174, 59972);
        U = Y(U, X, W, V, E[R + 4], 11, 19422, 53161);
        V = Y(V, U, X, W, E[R + 7], 16, 63163, 19296);
        W = Y(W, V, U, X, E[R + 10], 23, 48831, 48240);
        X = Y(X, W, V, U, E[R + 13], 4, 10395, 32454);
        U = Y(U, X, W, V, E[R + 0], 11, 60065, 10234);
        V = Y(V, U, X, W, E[R + 3], 16, 54511, 12421);
        W = Y(W, V, U, X, E[R + 6], 23, 1160, 7429);
        X = Y(X, W, V, U, E[R + 9], 4, 55764, 53305);
        U = Y(U, X, W, V, E[R + 12], 11, 59099, 39397);
        V = Y(V, U, X, W, E[R + 15], 16, 8098, 31992);
        W = Y(W, V, U, X, E[R + 2], 23, 50348, 22117);
        X = B(X, W, V, U, E[R + 0], 6, 62505, 8772);
        U = B(U, X, W, V, E[R + 7], 10, 17194, 65431);
        V = B(V, U, X, W, E[R + 14], 15, 43924, 9127);
        W = B(W, V, U, X, E[R + 5], 21, 64659, 41017);
        X = B(X, W, V, U, E[R + 12], 6, 25947, 22979);
        U = B(U, X, W, V, E[R + 3], 10, 36620, 52370);
        V = B(V, U, X, W, E[R + 10], 15, 65519, 62589);
        W = B(W, V, U, X, E[R + 1], 21, 34180, 24017);
        X = B(X, W, V, U, E[R + 8], 6, 28584, 32335);
        U = B(U, X, W, V, E[R + 15], 10, 65068, 59104);
        V = B(V, U, X, W, E[R + 6], 15, 41729, 17172);
        W = B(W, V, U, X, E[R + 13], 21, 19976, 4513);
        X = B(X, W, V, U, E[R + 4], 6, 63315, 32386);
        U = B(U, X, W, V, E[R + 11], 10, 48442, 62005);
        V = B(V, U, X, W, E[R + 2], 15, 10967, 53947);
        W = B(W, V, U, X, E[R + 9], 21, 60294, 54161);
        X = X.aU(S);
        W = W.aU(A);
        V = V.aU(D);
        U = U.aU(Q)
    }
    return X.gH() + W.gH() + V.gH() + U.gH()
};

function dW(A, B) {
    this.wa = new Array(2), this.wa[0] = A;
    this.wa[1] = B
}

dW.prototype.m = function () {
    return this.wa[0]
};
dW.prototype.l = function () {
    return this.wa[1]
};
dW.prototype.gH = function () {
    return tH(this.l()) + tH(this.m())
};

function tH(C) {
    var B = "0" + (C & 255).toString(16), A = "0" + ((C >>> 8) & 255).toString(16);
    return B.substr(B.length - 2, 2) + A.substr(A.length - 2, 2)
}

dW.prototype.aU = function (A) {
    var B = this.l() + A.l(), C = this.m() + A.m() + (B >> 16);
    return new dW(C & 65535, B & 65535)
};
dW.prototype.clf = function (C) {
    var B = this.m(), A = this.l();
    if (C < 16) {
        return lf(B, A, 16 - C, C, false)
    } else {
        if (C == 16) {
            return new dW(A, B)
        } else {
            return lf(B, A, 32 - C, C - 16, true)
        }
    }
};

function lf(I, A, H, G, B) {
    var D = (I >>> H) & (Math.pow(2, G) - 1), C = (A >>> H) & (Math.pow(2, G) - 1), F = ((I << G) | C) & 65535, E = ((A << G) | D) & 65535;
    if (B) {
        return new dW(E, F)
    } else {
        return new dW(F, E)
    }
}

dW.prototype.not = function () {
    return new dW((~this.m()) & 65535, (~this.l()) & 65535)
};
dW.prototype.or = function (A) {
    return new dW((this.m() | A.m()) & 65535, (this.l() | A.l()) & 65535)
};
dW.prototype.xor = function (A) {
    return new dW((this.m() ^ A.m()) & 65535, (this.l() ^ A.l()) & 65535)
};
dW.prototype.and = function (A) {
    return new dW((this.m() & A.m()) & 65535, (this.l() & A.l()) & 65535)
};

function am2(b, d, c, e, g, f) {
    var h = d & 32767;
    for (d >>= 15; 0 <= --f;) {
        var l = this[b] & 32767, k = this[b++] >> 15, q = d * l + k * h, l = h * l + ((q & 32767) << 15) + c[e] + (g & 1073741823);
        g = (l >>> 30) + (q >>> 15) + d * k + (g >>> 30);
        c[e++] = l & 1073741823
    }
    return g
}

function am3(b, d, c, e, g, f) {
    var h = d & 16383;
    for (d >>= 14; 0 <= --f;) {
        var l = this[b] & 16383, k = this[b++] >> 14, q = d * l + k * h, l = h * l + ((q & 16383) << 14) + c[e] + g;
        g = (l >> 28) + (q >> 14) + d * k;
        c[e++] = l & 268435455
    }
    return g
}

"Nokia" == navigator.appName ? (BigInteger.prototype.am = am3, dbits = 28) : j_lm && "Microsoft Internet Explorer" == navigator.appName ? (BigInteger.prototype.am = am2, dbits = 30) : j_lm && "Netscape" != navigator.appName ? (BigInteger.prototype.am = am1, dbits = 26) : (BigInteger.prototype.am = am3, dbits = 28);
BigInteger.prototype.DB = dbits;
BigInteger.prototype.DM = (1 << dbits) - 1;
BigInteger.prototype.DV = 1 << dbits;
var BI_FP = 52;
BigInteger.prototype.FV = Math.pow(2, BI_FP);
BigInteger.prototype.F1 = BI_FP - dbits;
BigInteger.prototype.F2 = 2 * dbits - BI_FP;
var BI_RM = "0123456789abcdefghijklmnopqrstuvwxyz", BI_RC = [], rr, vv;
rr = 48;
for (vv = 0; 9 >= vv; ++vv) BI_RC[rr++] = vv;
rr = 97;
for (vv = 10; 36 > vv; ++vv) BI_RC[rr++] = vv;
rr = 65;
for (vv = 10; 36 > vv; ++vv) BI_RC[rr++] = vv;

function int2char(b) {
    return BI_RM.charAt(b)
}

function intAt(b, d) {
    var c = BI_RC[b.charCodeAt(d)];
    return null == c ? -1 : c
}

function bnpCopyTo(b) {
    for (var d = this.t - 1; 0 <= d; --d) b[d] = this[d];
    b.t = this.t;
    b.s = this.s
}

function bnpFromInt(b) {
    this.t = 1;
    this.s = 0 > b ? -1 : 0;
    0 < b ? this[0] = b : -1 > b ? this[0] = b + DV : this.t = 0
}

function nbv(b) {
    var d = nbi();
    d.fromInt(b);
    return d
}

function bnpFromString(b, d) {
    var c;
    if (16 == d) c = 4; else if (8 == d) c = 3; else if (256 == d) c = 8; else if (2 == d) c = 1; else if (32 == d) c = 5; else if (4 == d) c = 2; else {
        this.fromRadix(b, d);
        return
    }
    this.s = this.t = 0;
    for (var e = b.length, g = !1, f = 0; 0 <= --e;) {
        var h = 8 == c ? b[e] & 255 : intAt(b, e);
        0 > h ? "-" == b.charAt(e) && (g = !0) : (g = !1, 0 == f ? this[this.t++] = h : f + c > this.DB ? (this[this.t - 1] |= (h & (1 << this.DB - f) - 1) << f, this[this.t++] = h >> this.DB - f) : this[this.t - 1] |= h << f, f += c, f >= this.DB && (f -= this.DB))
    }
    8 == c && 0 != (b[0] & 128) && (this.s = -1, 0 < f && (this[this.t - 1] |= (1 << this.DB -
        f) - 1 << f));
    this.clamp();
    g && BigInteger.ZERO.subTo(this, this)
}

function bnpClamp() {
    for (var b = this.s & this.DM; 0 < this.t && this[this.t - 1] == b;) --this.t
}

function bnToString(b) {
    if (0 > this.s) return "-" + this.negate().toString(b);
    var d;
    if (16 == b) d = 4; else if (8 == b) d = 3; else if (2 == b) d = 1; else if (32 == b) d = 5; else if (4 == b) d = 2; else return this.toRadix(b);
    var c = (1 << d) - 1, e, g = !1, f = "", h = this.t, l = this.DB - h * this.DB % d;
    if (0 < h--) for (l < this.DB && 0 < (e = this[h] >> l) && (g = !0, f = int2char(e)); 0 <= h;) l < d ? (e = (this[h] & (1 << l) - 1) << d - l, e |= this[--h] >> (l += this.DB - d)) : (e = this[h] >> (l -= d) & c, 0 >= l && (l += this.DB, --h)), 0 < e && (g = !0), g && (f += int2char(e));
    16 == b && 0 < f.length % 2 && (f = "0" + f);
    return g ? f : "0"
}

function bnNegate() {
    var b = nbi();
    BigInteger.ZERO.subTo(this, b);
    return b
}

function bnAbs() {
    return 0 > this.s ? this.negate() : this
}

function bnCompareTo(b) {
    var d = this.s - b.s;
    if (0 != d) return d;
    var c = this.t, d = c - b.t;
    if (0 != d) return d;
    for (; 0 <= --c;) if (0 != (d = this[c] - b[c])) return d;
    return 0
}

function nbits(b) {
    var d = 1, c;
    0 != (c = b >>> 16) && (b = c, d += 16);
    0 != (c = b >> 8) && (b = c, d += 8);
    0 != (c = b >> 4) && (b = c, d += 4);
    0 != (c = b >> 2) && (b = c, d += 2);
    0 != b >> 1 && (d += 1);
    return d
}

function bnBitLength() {
    return 0 >= this.t ? 0 : this.DB * (this.t - 1) + nbits(this[this.t - 1] ^ this.s & this.DM)
}

function bnpDLShiftTo(b, d) {
    var c;
    for (c = this.t - 1; 0 <= c; --c) d[c + b] = this[c];
    for (c = b - 1; 0 <= c; --c) d[c] = 0;
    d.t = this.t + b;
    d.s = this.s
}

function bnpDRShiftTo(b, d) {
    for (var c = b; c < this.t; ++c) d[c - b] = this[c];
    d.t = Math.max(this.t - b, 0);
    d.s = this.s
}

function bnpLShiftTo(b, d) {
    var c = b % this.DB, e = this.DB - c, g = (1 << e) - 1, f = Math.floor(b / this.DB), h = this.s << c & this.DM, l;
    for (l = this.t - 1; 0 <= l; --l) d[l + f + 1] = this[l] >> e | h, h = (this[l] & g) << c;
    for (l = f - 1; 0 <= l; --l) d[l] = 0;
    d[f] = h;
    d.t = this.t + f + 1;
    d.s = this.s;
    d.clamp()
}

function bnpRShiftTo(b, d) {
    d.s = this.s;
    var c = Math.floor(b / this.DB);
    if (c >= this.t) d.t = 0; else {
        var e = b % this.DB, g = this.DB - e, f = (1 << e) - 1;
        d[0] = this[c] >> e;
        for (var h = c + 1; h < this.t; ++h) d[h - c - 1] |= (this[h] & f) << g, d[h - c] = this[h] >> e;
        0 < e && (d[this.t - c - 1] |= (this.s & f) << g);
        d.t = this.t - c;
        d.clamp()
    }
}

function bnpSubTo(b, d) {
    for (var c = 0, e = 0, g = Math.min(b.t, this.t); c < g;) e += this[c] - b[c], d[c++] = e & this.DM, e >>= this.DB;
    if (b.t < this.t) {
        for (e -= b.s; c < this.t;) e += this[c], d[c++] = e & this.DM, e >>= this.DB;
        e += this.s
    } else {
        for (e += this.s; c < b.t;) e -= b[c], d[c++] = e & this.DM, e >>= this.DB;
        e -= b.s
    }
    d.s = 0 > e ? -1 : 0;
    -1 > e ? d[c++] = this.DV + e : 0 < e && (d[c++] = e);
    d.t = c;
    d.clamp()
}

function bnpMultiplyTo(b, d) {
    var c = this.abs(), e = b.abs(), g = c.t;
    for (d.t = g + e.t; 0 <= --g;) d[g] = 0;
    for (g = 0; g < e.t; ++g) d[g + c.t] = c.am(0, e[g], d, g, 0, c.t);
    d.s = 0;
    d.clamp();
    this.s != b.s && BigInteger.ZERO.subTo(d, d)
}

function bnpSquareTo(b) {
    for (var d = this.abs(), c = b.t = 2 * d.t; 0 <= --c;) b[c] = 0;
    for (c = 0; c < d.t - 1; ++c) {
        var e = d.am(c, d[c], b, 2 * c, 0, 1);
        (b[c + d.t] += d.am(c + 1, 2 * d[c], b, 2 * c + 1, e, d.t - c - 1)) >= d.DV && (b[c + d.t] -= d.DV, b[c + d.t + 1] = 1)
    }
    0 < b.t && (b[b.t - 1] += d.am(c, d[c], b, 2 * c, 0, 1));
    b.s = 0;
    b.clamp()
}

function bnpDivRemTo(b, d, c) {
    var e = b.abs();
    if (!(0 >= e.t)) {
        var g = this.abs();
        if (g.t < e.t) null != d && d.fromInt(0), null != c && this.copyTo(c); else {
            null == c && (c = nbi());
            var f = nbi(), h = this.s;
            b = b.s;
            var l = this.DB - nbits(e[e.t - 1]);
            0 < l ? (e.lShiftTo(l, f), g.lShiftTo(l, c)) : (e.copyTo(f), g.copyTo(c));
            e = f.t;
            g = f[e - 1];
            if (0 != g) {
                var k = g * (1 << this.F1) + (1 < e ? f[e - 2] >> this.F2 : 0), q = this.FV / k, k = (1 << this.F1) / k, u = 1 << this.F2, r = c.t, t = r - e,
                    s = null == d ? nbi() : d;
                f.dlShiftTo(t, s);
                0 <= c.compareTo(s) && (c[c.t++] = 1, c.subTo(s, c));
                BigInteger.ONE.dlShiftTo(e,
                    s);
                for (s.subTo(f, f); f.t < e;) f[f.t++] = 0;
                for (; 0 <= --t;) {
                    var p = c[--r] == g ? this.DM : Math.floor(c[r] * q + (c[r - 1] + u) * k);
                    if ((c[r] += f.am(0, p, c, t, 0, e)) < p) for (f.dlShiftTo(t, s), c.subTo(s, c); c[r] < --p;) c.subTo(s, c)
                }
                null != d && (c.drShiftTo(e, d), h != b && BigInteger.ZERO.subTo(d, d));
                c.t = e;
                c.clamp();
                0 < l && c.rShiftTo(l, c);
                0 > h && BigInteger.ZERO.subTo(c, c)
            }
        }
    }
}

function Classic(b) {
    this.m = b
}

function cConvert(b) {
    return 0 > b.s || 0 <= b.compareTo(this.m) ? b.mod(this.m) : b
}

function cRevert(b) {
    return b
}

function cReduce(b) {
    b.divRemTo(this.m, null, b)
}

function cMulTo(b, d, c) {
    b.multiplyTo(d, c);
    this.reduce(c)
}

function cSqrTo(b, d) {
    b.squareTo(d);
    this.reduce(d)
}

Classic.prototype.convert = cConvert;
Classic.prototype.revert = cRevert;
Classic.prototype.reduce = cReduce;
Classic.prototype.mulTo = cMulTo;
Classic.prototype.sqrTo = cSqrTo;

function bnpInvDigit() {
    if (1 > this.t) return 0;
    var b = this[0];
    if (0 == (b & 1)) return 0;
    var d = b & 3, d = d * (2 - (b & 15) * d) & 15, d = d * (2 - (b & 255) * d) & 255, d = d * (2 - ((b & 65535) * d & 65535)) & 65535, d = d * (2 - b * d % this.DV) % this.DV;
    return 0 < d ? this.DV - d : -d
}

function Montgomery(b) {
    this.m = b;
    this.mp = b.invDigit();
    this.mpl = this.mp & 32767;
    this.mph = this.mp >> 15;
    this.um = (1 << b.DB - 15) - 1;
    this.mt2 = 2 * b.t
}

function montConvert(b) {
    var d = nbi();
    b.abs().dlShiftTo(this.m.t, d);
    d.divRemTo(this.m, null, d);
    0 > b.s && 0 < d.compareTo(BigInteger.ZERO) && this.m.subTo(d, d);
    return d
}

function montRevert(b) {
    var d = nbi();
    b.copyTo(d);
    this.reduce(d);
    return d
}

function montReduce(b) {
    for (; b.t <= this.mt2;) b[b.t++] = 0;
    for (var d = 0; d < this.m.t; ++d) {
        var c = b[d] & 32767, e = c * this.mpl + ((c * this.mph + (b[d] >> 15) * this.mpl & this.um) << 15) & b.DM, c = d + this.m.t;
        for (b[c] += this.m.am(0, e, b, d, 0, this.m.t); b[c] >= b.DV;) b[c] -= b.DV, b[++c]++
    }
    b.clamp();
    b.drShiftTo(this.m.t, b);
    0 <= b.compareTo(this.m) && b.subTo(this.m, b)
}

function montSqrTo(b, d) {
    b.squareTo(d);
    this.reduce(d)
}

function montMulTo(b, d, c) {
    b.multiplyTo(d, c);
    this.reduce(c)
}

Montgomery.prototype.convert = montConvert;
Montgomery.prototype.revert = montRevert;
Montgomery.prototype.reduce = montReduce;
Montgomery.prototype.mulTo = montMulTo;
Montgomery.prototype.sqrTo = montSqrTo;

function bnpIsEven() {
    return 0 == (0 < this.t ? this[0] & 1 : this.s)
}

function bnpExp(b, d) {
    var c = nbi(), e = nbi(), g = d.convert(this), f = nbits(b) - 1;
    for (g.copyTo(c); 0 <= --f;) if (d.sqrTo(c, e), 0 < (b & 1 << f)) d.mulTo(e, g, c); else var h = c, c = e, e = h;
    return d.revert(c)
}

function bnModPowInt(b, d) {
    var c;
    c = 256 > b || d.isEven() ? new Classic(d) : new Montgomery(d);
    return this.exp(b, c)
}

function bnpBitwiseTo(b, d, c) {
    var e, g, f = Math.min(b.t, this.t);
    for (e = 0; e < f; ++e) c[e] = d(this[e], b[e]);
    if (b.t < this.t) {
        g = b.s & this.DM;
        for (e = f; e < this.t; ++e) c[e] = d(this[e], g);
        c.t = this.t
    } else {
        g = this.s & this.DM;
        for (e = f; e < b.t; ++e) c[e] = d(g, b[e]);
        c.t = b.t
    }
    c.s = d(this.s, b.s);
    c.clamp()
}

function op_xor(b, d) {
    return b ^ d
}

function bnXor(b) {
    var d = nbi();
    this.bitwiseTo(b, op_xor, d);
    return d
}

function lbit(b) {
    if (0 == b) return -1;
    var d = 0;
    0 == (b & 65535) && (b >>= 16, d += 16);
    0 == (b & 255) && (b >>= 8, d += 8);
    0 == (b & 15) && (b >>= 4, d += 4);
    0 == (b & 3) && (b >>= 2, d += 2);
    0 == (b & 1) && ++d;
    return d
}

BigInteger.prototype.copyTo = bnpCopyTo;
BigInteger.prototype.fromInt = bnpFromInt;
BigInteger.prototype.fromString = bnpFromString;
BigInteger.prototype.clamp = bnpClamp;
BigInteger.prototype.dlShiftTo = bnpDLShiftTo;
BigInteger.prototype.subTo = bnpSubTo;
BigInteger.prototype.rShiftTo = bnpRShiftTo;
BigInteger.prototype.drShiftTo = bnpDRShiftTo;
BigInteger.prototype.invDigit = bnpInvDigit;
BigInteger.prototype.isEven = bnpIsEven;
BigInteger.prototype.multiplyTo = bnpMultiplyTo;
BigInteger.prototype.lShiftTo = bnpLShiftTo;
BigInteger.prototype.divRemTo = bnpDivRemTo;
BigInteger.prototype.squareTo = bnpSquareTo;
BigInteger.prototype.exp = bnpExp;
BigInteger.prototype.bitwiseTo = bnpBitwiseTo;
BigInteger.prototype.toString = bnToString;
BigInteger.prototype.negate = bnNegate;
BigInteger.prototype.abs = bnAbs;
BigInteger.prototype.compareTo = bnCompareTo;
BigInteger.prototype.bitLength = bnBitLength;
BigInteger.prototype.modPowInt = bnModPowInt;
BigInteger.prototype.xor = bnXor;
BigInteger.ZERO = nbv(0);
BigInteger.ONE = nbv(1);

function parseBigInt(b, d) {
    return new BigInteger(b, d)
}

function pkcs1pad2B(b, d) {
    var c = b.length;
    if (c > d - 11 - 4) throw"104";
    c = randomBytes(d - c - 3 - 4);
    c = [0, 2, 255, 255, 255, 255].concat(c, [0], b);
    return new BigInteger(c)
}

function randomBytes(b) {
    for (var d = [], c = 0, c = 0; c < b; c++) d[c] = Math.ceil(255 * Math.random());
    return d
}

function pkcs1pad2(b, d) {
    var c = Math.ceil(b.bitLength() / 8);
    if (d < c + 11 + 4) return alert("Message too long for RSA"), null;
    for (var e = [0, 2, 255, 255, 255, 255], c = d - c - 7, g = 0, f = 6; f < c + 6;) {
        for (g = 0; 0 == g;) g = Math.floor(255 * Math.random());
        e[f++] = g
    }
    e = (new BigInteger(e)).toString(16) + "00" + b.toString(16);
    return new BigInteger(e, 16)
}

function pkcs1pad2S(b, d) {
    var c = Math.ceil(b.bitLength() / 8);
    if (d < c + 11) return alert("Message too long for RSA"), null;
    for (var e = [0, 2], c = d - c - 3, g = 2; g < c + 2;) {
        for (var f = 0; 0 == f;) f = Math.floor(255 * Math.random());
        e[g++] = f
    }
    e = (new BigInteger(e)).toString(16) + "00" + b.toString(16);
    return new BigInteger(e, 16)
}

function RSAKey() {
    this.n = null;
    this.e = 0;
    this.d = null
}

RSAKey.prototype.setPublic = function (b, d) {
    null != b && null != d && 0 < b.length && 0 < d.length ? (this.n = parseBigInt(b, 16), this.e = parseInt(d, 16)) : alert("Invalid RSA public key")
};
RSAKey.prototype.doPublic = function (b) {
    return b.modPowInt(this.e, this.n)
};
RSAKey.prototype.encryptNativeHexStr = function (b) {
    var d = b.length / 2, c = this.n.bitLength() + 7 >> 3;
    if (d > c) throw"104";
    b = new BigInteger(b, 16);
    b = this.doPublic(b);
    if (null == b) return null;
    b = b.toString(16);
    if (256 < b.length) return null;
    if (256 > b.length) for (d = 0; d < 256 - b.length; d++) b = "0" + b;
    return b
};
RSAKey.prototype.encryptNativeBytes = function (b) {
    var d = b.length;
    alert("n:" + this.n);
    var c = this.n.bitLength() + 7 >> 3;
    if (d > c) throw"104";
    b = new BigInteger(b);
    alert("m:" + b);
    b = this.doPublic(b);
    if (null == b) return null;
    b = b.toString(16);
    if (256 < b.length) return null;
    if (256 > b.length) for (d = 0; d < 256 - b.length; d++) b = "0" + b;
    return b
};
RSAKey.prototype.encryptS = function (b) {
    b = pkcs1pad2S(b, this.n.bitLength() + 7 >> 3);
    if (null == b) return null;
    b = this.doPublic(b);
    if (null == b) return null;
    b = b.toString(16);
    if (256 < b.length) return null;
    if (256 > b.length) for (var d = 0; d < 256 - b.length; d++) b = "0" + b;
    return b
};
RSAKey.prototype.encrypt = function (b) {
    b = pkcs1pad2(b, this.n.bitLength() + 7 >> 3);
    if (null == b) return null;
    b = this.doPublic(b);
    if (null == b) return null;
    b = b.toString(16);
    var maxSize = 256;
    if (Is2048 == true) {
        maxSize = 512
    }
    if (maxSize < b.length) return null;
    if (maxSize > b.length) for (var d = 0; d < maxSize - b.length; d++) b = "0" + b;
    return b
};
RSAKey.prototype.encryptB = function (b) {
    b = pkcs1pad2B(b, this.n.bitLength() + 7 >> 3);
    if (null == b) return null;
    b = this.doPublic(b);
    if (null == b) return null;
    b = b.toString(16);
    if (256 < b.length) return null;
    if (256 > b.length) for (var d = 0; d < 256 - b.length; d++) b = "0" + b;
    return b
};

function sha256Hash(b) {
    var d = [1116352408, 1899447441, 3049323471, 3921009573, 961987163, 1508970993, 2453635748, 2870763221, 3624381080, 310598401, 607225278, 1426881987, 1925078388, 2162078206, 2614888103, 3248222580, 3835390401, 4022224774, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, 2554220882, 2821834349, 2952996808, 3210313671, 3336571891, 3584528711, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, 2177026350, 2456956037, 2730485921, 2820302411, 3259730800, 3345764771,
            3516065817, 3600352804, 4094571909, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, 2227730452, 2361852424, 2428436474, 2756734187, 3204031479, 3329325298],
        c = [1779033703, 3144134277, 1013904242, 2773480762, 1359893119, 2600822924, 528734635, 1541459225];
    b += String.fromCharCode(128);
    for (var e = Math.ceil((b.length / 4 + 2) / 16), g = Array(e), f = 0; f < e; f++) {
        g[f] = Array(16);
        for (var h = 0; 16 > h; h++) g[f][h] = b.charCodeAt(64 * f + 4 * h) << 24 | b.charCodeAt(64 * f + 4 * h + 1) << 16 | b.charCodeAt(64 *
            f + 4 * h + 2) << 8 | b.charCodeAt(64 * f + 4 * h + 3)
    }
    g[e - 1][14] = 8 * (b.length - 1) / Math.pow(2, 32);
    g[e - 1][14] = Math.floor(g[e - 1][14]);
    g[e - 1][15] = 8 * (b.length - 1) & 4294967295;
    b = Array(64);
    for (var l, k, q, u, r, t, s, f = 0; f < e; f++) {
        for (var p = 0; 16 > p; p++) b[p] = g[f][p];
        for (p = 16; 64 > p; p++) b[p] = sigma1(b[p - 2]) + b[p - 7] + sigma0(b[p - 15]) + b[p - 16] & 4294967295;
        h = c[0];
        l = c[1];
        k = c[2];
        q = c[3];
        u = c[4];
        r = c[5];
        t = c[6];
        s = c[7];
        for (p = 0; 64 > p; p++) {
            var v = s + Sigma1(u) + Ch(u, r, t) + d[p] + b[p], w = Sigma0(h) + Maj(h, l, k);
            s = t;
            t = r;
            r = u;
            u = q + v & 4294967295;
            q = k;
            k = l;
            l = h;
            h = v + w & 4294967295
        }
        c[0] =
            c[0] + h & 4294967295;
        c[1] = c[1] + l & 4294967295;
        c[2] = c[2] + k & 4294967295;
        c[3] = c[3] + q & 4294967295;
        c[4] = c[4] + u & 4294967295;
        c[5] = c[5] + r & 4294967295;
        c[6] = c[6] + t & 4294967295;
        c[7] = c[7] + s & 4294967295
    }
    return c[0].toHexStr() + c[1].toHexStr() + c[2].toHexStr() + c[3].toHexStr() + c[4].toHexStr() + c[5].toHexStr() + c[6].toHexStr() + c[7].toHexStr()
}

function ROTR(b, d) {
    return d >>> b | d << 32 - b
}

function Sigma0(b) {
    return ROTR(2, b) ^ ROTR(13, b) ^ ROTR(22, b)
}

function Sigma1(b) {
    return ROTR(6, b) ^ ROTR(11, b) ^ ROTR(25, b)
}

function sigma0(b) {
    return ROTR(7, b) ^ ROTR(18, b) ^ b >>> 3
}

function sigma1(b) {
    return ROTR(17, b) ^ ROTR(19, b) ^ b >>> 10
}

function Ch(b, d, c) {
    return b & d ^ ~b & c
}

function Maj(b, d, c) {
    return b & d ^ b & c ^ d & c
}

Number.prototype.toHexStr = function () {
    for (var b = "", d, c = 7; 0 <= c; c--) d = this >>> 4 * c & 15, b += d.toString(16);
    return b
};

function Util() {
}

Util.parseBigInt = function (b, d) {
    return new BigInteger(b, d)
};
Util.randomString = function (b) {
    for (var d = "", c = 0, c = 0; c < b; c++) d += String.fromCharCode(Math.ceil(255 * Math.random()));
    return d
};
Util.randomBytes = function (b) {
    for (var d = [], c = 0, c = 0; c < b; c++) d[c] = Math.ceil(255 * Math.random());
    return d
};
Util.toHexString = function (b) {
    for (var d = "", c = 0; c < b.length; c++) {
        var e;
        "number" == typeof b[c] ? e = b[c].toString(16) : "string" == typeof b[c] && (e = b.charCodeAt(c).toString(16));
        1 == e.length && (e = "0" + e);
        d += e
    }
    return d
};
Util.fromHexString = function (b) {
    b = 0 == b.length % 2 ? b : "0" + b;
    for (var d = b.length / 2, c = [], e = 0, g = 0; e < d; e++, g++) {
        var f = 2 * e;
        c[g] = parseInt("0x" + b.substring(f, f + 2))
    }
    return c
};
Util.fromHexToString = function (b) {
    b = 0 == b.length % 2 ? b : "0" + b;
    for (var d = b.length / 2, c = "", e = 0; e < d; e++) var g = 2 * e, c = c + String.fromCharCode(parseInt("0x" + b.substring(g, g + 2)));
    return c
};
Util.cByteArrayToNString = function (b) {
    for (var d = "", c = 0; c < b.length; c++) d += String.fromCharCode(b[c]);
    return d
};
Util.getByteArray = function (b) {
    var a = [];
    for (var d = 0; d < b.length; d++) a[d] = b.charCodeAt(d);
    return a
};
Util.xorByteArray = function (b, d) {
    if (b.length > d.length) throw"Invalid parameters.";
    for (var c = [], e = 0; e < b.length; e++) c[e] = b[e] ^ d[e];
    return c
};
Util.stringToHex = function (b) {
    for (var d = "", c = "0123456789abcdef".split(""), e = 0; e < b.length; e++) d += c[b.charCodeAt(e) >> 4] + c[b.charCodeAt(e) & 15];
    return d
};

exports = {
    encryptVerifyStaticRSABlock256: encryptVerifyStaticRSABlock256,
    encryptSetPwdNoVerifyRSABlock256: encryptSetPwdNoVerifyRSABlock256
}