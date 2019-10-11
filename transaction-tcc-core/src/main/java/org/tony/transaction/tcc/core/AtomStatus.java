package org.tony.transaction.tcc.core;


//**
public enum AtomStatus  {
    CRT {
        @Override
        public AtomStatus success() {
            return TRY;
        }
        @Override
        public AtomStatus error() {
            return null;
        }
        @Override
        public boolean isExecTry() {
            return true;
        }
    },
    TRY {
        @Override
        public AtomStatus success() {
            return CFM;
        }
        @Override
        public AtomStatus error() {
            return UNDO;
        }
        @Override
        public AtomStatus query() {
            return QUERY;
        }
        public boolean isExecRollback() {
            return true;
        }
    },
    QUERY {
        @Override
        public AtomStatus success() {
            return CFM;
        }
        @Override
        public AtomStatus error() {
            return UNDO;
        }
        @Override
        public boolean isExecRollback() {
            return true;
        }
    },
    UNDO {
        @Override
        public AtomStatus success() {
            return FINISH;
        }
        @Override
        public AtomStatus error() {
            return UNDO;
        }
        @Override
        public boolean isExecRollback() {
            return true;
        }
    },
    CFM {
        @Override
        public AtomStatus success() {
            return FINISH;
        }
        @Override
        public AtomStatus error() {
            return RETRY;
        }
        @Override
        public boolean isExecRollback() {
            return true;
        }
    },
    FINISH {
        @Override
        public AtomStatus success() {
            return null;
        }

        @Override
        public AtomStatus error() {
            return null;
        }
        @Override
        public boolean isEndPoint() {
            return true;
        }
    },
    RETRY {
        @Override
        public AtomStatus success() {
            return FINISH;
        }
        @Override
        public AtomStatus error() {
            return RETRY;
        }
    },
    MANUAL {
        @Override
        public AtomStatus success() {
            notSupport();
            return null;
        }
        @Override
        public AtomStatus error() {
            notSupport();
            return null;
        }
        @Override
        public boolean isEndPoint() {
            return true;
        }
    }
    ;
    public abstract AtomStatus success();

    public abstract AtomStatus error();

    public AtomStatus query() {
        notSupport();
        return null;
    }
    
    public boolean isEndPoint() {
        return false;
    }
    public  boolean isExecTry() {
        return false;
    }
    public  boolean isExecRollback() {
        return false;
    }
    
    void notSupport() {
        throw new TccTransactionException("Current status `" + name() + "` does not support this method");
    }
}
