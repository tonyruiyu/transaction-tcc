package org.tony.transaction.tcc.test;

import java.util.List;

import com.alibaba.fastjson.JSON;
import org.tony.transaction.tcc.core.Atom;
import org.tony.transaction.tcc.core.AtomStatus;
import org.tony.transaction.tcc.core.Composite;

public class Result {
    
    public Result() {}

    private Composite                   composite;

    private List<Atom>                  atoms;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    private boolean    isTryErrorb = false;

    private boolean    isUndob     = true;

    private AtomStatus atomStatusb = AtomStatus.CFM;

    private boolean    isTryErrors = false;

    private boolean    isUndos     = true;

    private AtomStatus atomStatuss = AtomStatus.CFM;

    public Composite getComposite() {
        return composite;
    }

    public void setComposite(Composite composite) {
        this.composite = composite;
    }

    public List<Atom> getAtoms() {
        return atoms;
    }

    public void setAtoms(List<Atom> atoms) {
        this.atoms = atoms;
    }

    public boolean isTryErrorb() {
        return isTryErrorb;
    }

    public void setTryErrorb(boolean isTryErrorb) {
        this.isTryErrorb = isTryErrorb;
    }

    public boolean isUndob() {
        return isUndob;
    }

    public void setUndob(boolean isUndob) {
        this.isUndob = isUndob;
    }

    public AtomStatus getAtomStatusb() {
        return atomStatusb;
    }

    public void setAtomStatusb(AtomStatus atomStatusb) {
        this.atomStatusb = atomStatusb;
    }

    public boolean isTryErrors() {
        return isTryErrors;
    }

    public void setTryErrors(boolean isTryErrors) {
        this.isTryErrors = isTryErrors;
    }

    public boolean isUndos() {
        return isUndos;
    }

    public void setUndos(boolean isUndos) {
        this.isUndos = isUndos;
    }

    public AtomStatus getAtomStatuss() {
        return atomStatuss;
    }

    public void setAtomStatuss(AtomStatus atomStatuss) {
        this.atomStatuss = atomStatuss;
    }
    
    

}
