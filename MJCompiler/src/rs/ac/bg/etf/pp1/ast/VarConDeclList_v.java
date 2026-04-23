// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class VarConDeclList_v extends VarConDeclList {

    private VarConDeclList VarConDeclList;
    private VarDecl VarDecl;

    public VarConDeclList_v (VarConDeclList VarConDeclList, VarDecl VarDecl) {
        this.VarConDeclList=VarConDeclList;
        if(VarConDeclList!=null) VarConDeclList.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public VarConDeclList getVarConDeclList() {
        return VarConDeclList;
    }

    public void setVarConDeclList(VarConDeclList VarConDeclList) {
        this.VarConDeclList=VarConDeclList;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarConDeclList!=null) VarConDeclList.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarConDeclList!=null) VarConDeclList.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarConDeclList!=null) VarConDeclList.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarConDeclList_v(\n");

        if(VarConDeclList!=null)
            buffer.append(VarConDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarConDeclList_v]");
        return buffer.toString();
    }
}
