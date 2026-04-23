// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class YesVarMore extends VarMore {

    private VarDeclOne VarDeclOne;
    private VarMore VarMore;

    public YesVarMore (VarDeclOne VarDeclOne, VarMore VarMore) {
        this.VarDeclOne=VarDeclOne;
        if(VarDeclOne!=null) VarDeclOne.setParent(this);
        this.VarMore=VarMore;
        if(VarMore!=null) VarMore.setParent(this);
    }

    public VarDeclOne getVarDeclOne() {
        return VarDeclOne;
    }

    public void setVarDeclOne(VarDeclOne VarDeclOne) {
        this.VarDeclOne=VarDeclOne;
    }

    public VarMore getVarMore() {
        return VarMore;
    }

    public void setVarMore(VarMore VarMore) {
        this.VarMore=VarMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclOne!=null) VarDeclOne.accept(visitor);
        if(VarMore!=null) VarMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclOne!=null) VarDeclOne.traverseTopDown(visitor);
        if(VarMore!=null) VarMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclOne!=null) VarDeclOne.traverseBottomUp(visitor);
        if(VarMore!=null) VarMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesVarMore(\n");

        if(VarDeclOne!=null)
            buffer.append(VarDeclOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarMore!=null)
            buffer.append(VarMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesVarMore]");
        return buffer.toString();
    }
}
