package org.tony.transaction.tcc.core;

public enum CompositeStatus {
    /** * 组合单创建初始状态 */
    CRT {
        @Override
        public CompositeStatus success() {
            return TRY;
        }
        @Override
        public CompositeStatus error() {
            notSupport();
            return null;
        }
        /**
         * 可以执行TRY
         */
        @Override
        public boolean isExecTry() {
            return true;
        }
    },
    /**
     * 开始执行try 阶段状态
     */
    TRY {
        @Override
        public CompositeStatus success() {
            return CFM;
        }
        @Override
        public CompositeStatus error() {
            return UNDO;
        }
        public CompositeStatus unknow() {
            return UNKNOW;
        }
        public boolean isExecTry() {
            return true;
        }
    },
    /**
     * 第二阶段提交开始状态
     */
    CFM {
        @Override
        public CompositeStatus success() {
            return FINISH;
        }

        @Override
        public CompositeStatus error() {
            return RETRY;
        }
        
        public boolean isCfm() {
            return true;
        }
        
    },/**回滚*/
    UNDO {
        @Override
        public CompositeStatus success() {
            return FINISH;
        }

        @Override
        public CompositeStatus error() {
            return UNDO;
        }
    },/**未知*/
    UNKNOW {
        @Override
        public CompositeStatus success() {
            return CFM;
        }
        @Override
        public CompositeStatus error() {
            return UNDO;
        }
        public CompositeStatus unknow() {
            return UNKNOW;
        }
        public boolean isExecTry() {
            return true;
        }
    },
    RETRY {
        @Override
        public CompositeStatus success() {
            return FINISH;
        }
        @Override
        public CompositeStatus error() {
            return RETRY;
        }
        public boolean isCfm() {
            return true;
        }
    },
    FINISH {
        @Override
        public CompositeStatus success() {
            notSupport();
            return null;
        }
        @Override
        public CompositeStatus error() {
            notSupport();
            return null;
        }

        public boolean isEndPoint() {
            return true;
        }
    },
    MANUAL {
        @Override
        public CompositeStatus success() {
            notSupport();
            return null;
        }
        @Override
        public CompositeStatus error() {
            notSupport();
            return null;
        }
        public boolean isEndPoint() {
            return true;
        }
    };
    public abstract CompositeStatus success();
    public abstract CompositeStatus error();
    public boolean isExecTry() {
        return false;
    };
    
    public boolean isCfm() {
        return false;
    }
    public boolean isEndPoint() {
        return false;
    }

    public CompositeStatus unknow() {
        notSupport();
        return null;
    }
    

    void notSupport() {
        throw new TccTransactionException("Current status `" + name() + "` does not support this method");
    }
}
