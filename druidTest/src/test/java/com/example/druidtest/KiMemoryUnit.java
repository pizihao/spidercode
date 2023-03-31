package com.example.druidtest;

/**
 * 内存单位。仿照{@link java.util.concurrent.TimeUnit}
 * 按照 Kilobyte，Megabyte，Gigabyte，Terabyte等进行换算
 *
 * @author liuwenhao
 * @date 2023年3月30日11:39:54
 */
public enum KiMemoryUnit {

    BYTES {
        @Override
        public long toBytes(long d) {
            return d;
        }

        @Override
        public long toKilobytes(long d) {
            return toBytes(d) / s;
        }

        @Override
        public long toMegabytes(long d) {
            return toKilobytes(d) / s;
        }

        @Override
        public long toGigabytes(long d) {
            return toMegabytes(d) / s;
        }

        @Override
        public long toTerabytes(long d) {
            return toGigabytes(d) / s;
        }

        @Override
        public String getUnit() {
            return "B";
        }
    },

    KILOBYTES {
        @Override
        public long toBytes(long d) {
            return x(d, s, MAX / s);
        }

        @Override
        public long toKilobytes(long d) {
            return d;
        }

        @Override
        public long toMegabytes(long d) {
            return toKilobytes(d) / s;
        }

        @Override
        public long toGigabytes(long d) {
            return toMegabytes(d) / s;
        }

        @Override
        public long toTerabytes(long d) {
            return toGigabytes(d) / s;
        }

        @Override
        public String getUnit() {
            return "Ki";
        }
    },

    MEGABYTES {
        @Override
        public long toBytes(long d) {
            return x(d, toKilobytes(s), MAX / s);
        }

        @Override
        public long toKilobytes(long d) {
            return x(d, s, MAX / s);
        }

        @Override
        public long toMegabytes(long d) {
            return d;
        }

        @Override
        public long toGigabytes(long d) {
            return toMegabytes(d) / s;
        }

        @Override
        public long toTerabytes(long d) {
            return toGigabytes(d) / s;
        }

        @Override
        public String getUnit() {
            return "Mi";
        }
    },

    GIGABYTES {
        @Override
        public long toBytes(long d) {
            return x(d, toKilobytes(s), MAX / s);
        }

        @Override
        public long toKilobytes(long d) {
            return x(d, toMegabytes(s), MAX / s);
        }

        @Override
        public long toMegabytes(long d) {
            return x(d, s, MAX / s);
        }

        @Override
        public long toGigabytes(long d) {
            return d;
        }

        @Override
        public long toTerabytes(long d) {
            return toGigabytes(d) / s;
        }

        @Override
        public String getUnit() {
            return "Gi";
        }
    },

    TERABYTES {
        @Override
        public long toBytes(long d) {
            return x(d, toKilobytes(s), MAX / s);
        }

        @Override
        public long toKilobytes(long d) {
            return x(d, toMegabytes(s), MAX / s);
        }

        @Override
        public long toMegabytes(long d) {
            return x(d, toGigabytes(s), MAX / s);
        }

        @Override
        public long toGigabytes(long d) {
            return x(d, s, MAX / s);
        }

        @Override
        public long toTerabytes(long d) {
            return d;
        }

        @Override
        public String getUnit() {
            return "Ti";
        }
    };

    static final long s = 1024;
    static final long MAX = Long.MAX_VALUE;

    static long x(long d, long m, long over) {
        if (d > over) return Long.MAX_VALUE;
        if (d < -over) return Long.MIN_VALUE;
        return d * m;
    }

    public long toBytes(long d) {
        throw new UnsupportedOperationException();
    }

    public long toKilobytes(long d) {
        throw new UnsupportedOperationException();
    }

    public long toMegabytes(long d) {
        throw new UnsupportedOperationException();
    }

    public long toGigabytes(long d) {
        throw new UnsupportedOperationException();
    }

    public long toTerabytes(long d) {
        throw new UnsupportedOperationException();
    }

    public String getUnit() {
        throw new UnsupportedOperationException();
    }

    public static KiMemoryUnit of(String company) {
        switch (company) {
            case "B":
            case "b":
            case "byte":
                return BYTES;
            case "Ki":
            case "KB":
            case "K":
                return KILOBYTES;
            case "Mi":
            case "MB":
            case "M":
                return MEGABYTES;
            case "Gi":
            case "GB":
            case "G":
                return GIGABYTES;
            case "Ti":
            case "TB":
            case "T":
                return TERABYTES;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
