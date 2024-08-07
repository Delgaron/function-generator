package Project;

public enum FunctionType {
    COSINE {
        @Override
        public double getFunctionType(double x) {
            return Math.cos(x);
        }
    },
    SINE {
        @Override
        public double getFunctionType(double x) {
            return Math.sin(x);
        }
    },
    TANGENT {
        @Override
        public double getFunctionType(double x) {
            return Math.tan(x);
        }
    },
    COTANGENT {
        @Override
        public double getFunctionType(double x) {
            return 1.0 / Math.tan(x);
        }
    },
    ARCSINE {
        @Override
        public double getFunctionType(double x) {
            return Math.asin(x);
        }
    },
    ARCCOSINE {
        @Override
        public double getFunctionType(double x) {
            return Math.acos(x);
        }
    },
    ARCTANGENT {
        @Override
        public double getFunctionType(double x) {
            return Math.atan(x);
        }
    },
    ARCCOTANGENT {
        @Override
        public double getFunctionType(double x) {
            return  Math.atan(1.0 / x);
        }
    };

    public abstract double getFunctionType(double x);
}