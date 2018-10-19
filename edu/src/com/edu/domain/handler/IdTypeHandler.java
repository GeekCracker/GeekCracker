package com.edu.domain.handler;

public class IdTypeHandler{
	
	
	/**
	 * long -> string 混淆加密
	*/
	public static String encode(long l) {
		l = mix(l);
		if (l < 0){
			System.out.println(l);
		}
		return Long.toString(l, 36);
	}

	public static long decode(String s) {
		return demix(Long.parseLong(s, 36));
	}

	//------------------------------------------------------
	
	/**
	 * 带版本的混淆
	*/
	private static long mix(long l) {
		long[] vs = doMix(l);
		return setVersion(vs);
	}

	/**
	 * 当前版本的mix算法. <b>注意不要数值越界成负数</b>
	*/
	private static long[] doMix(long l) {
		final long version = 1L; // 当前混淆算法版本号

		// 8进制位
		long ret = l;
		int digit = 0;
		while (ret > 0) {
			digit++;
			ret = ret >> 3;
		}
		// 每5位插值, 插值位
		int i = 0, md = (digit - 1) / 5 + 1, mix = (int) (l & ((1 << (3 * md)) - 1));
		ret = 0;
		while (digit > 0) {
			ret += (((l & ((1 << 15) - 1)) + ((mix & (((1 << 3) - 1) << (3 * --md))) << (15 - 3 * md))) << i);
			l = (l >> 15);
			digit -= 5;
			i += 18;
		}
		l = ret;

		return new long[] { version, l };
	}

	private static long demix(long l) {
		long[] vs = getVersion(l);
		l = vs[1];
		switch ((int) vs[0]) {
		case 1:
			long dig = 0,
			ret = 0;
			while (l > 0) {
				ret += ((l & ((1 << 15) - 1)) << dig);
				l = (l >> 18);
				dig += 15;
			}
			l = ret;
			break;
		}
		return l;
	}

	/**
	 * 16进制下，将版本号保留在百位数
	 * 
	 * @param [version, value]
	 */
	private static long setVersion(long[] vs) {
		// return vs[1] / 256 * 4096 + vs[0] * 256 + vs[1] % 256;
		return ((vs[1] >> 8) << 12) + (vs[0] << 8) + (vs[1] & 255);
	}

	/**
	 * 16进制下，将版本号保留在百位
	 * @return [version, value]
	*/
	private static long[] getVersion(long l) {
		// return new long[] { (l / 256) % 16, (l / 4096) * 256 + l % 256 };
		return new long[] { (l >> 8) & 15, ((l >> 12) << 8) + (l & 255) };
	}

	public static void main(String[] args) {


		System.out.println(encode(167));
		System.out.println(encode(1));
		System.out.println(decode("1k6x1"));

		System.out.println(encode(16));

	}
}
