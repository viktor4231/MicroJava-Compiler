// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class VarConDeclList_c extends VarConDeclList {

    private VarConDeclList VarConDeclList;
    private ConDecl ConDecl;

    public VarConDeclList_c (VarConDeclList VarConDeclList, ConDecl ConDecl) {
        this.VarConDeclList=VarConDeclList;
        if(VarConDeclList!=null) VarConDeclList.setParent(this);
        this.ConDecl=ConDecl;
        if(ConDecl!=null) ConDecl.setParent(this);
    }

    public VarConDeclList getVarConDeclList() {
        return VarConDeclList;
    }

    public void setVarConDeclList(VarConDeclList VarConDeclList) {
        this.VarConDeclList=VarConDeclList;
    }

    public ConDecl getConDecl() {
        return ConDecl;
    }

    public void setConDecl(ConDecl ConDecl) {
        this.ConDecl=ConDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarConDeclList!=null) VarConDeclList.accept(visitor);
        if(ConDecl!=null) ConDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarConDeclList!=null) VarConDeclList.traverseTopDown(visitor);
        if(ConDecl!=null) ConDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarConDeclList!=null) VarConDeclList.traverseBottomUp(visitor);
        if(ConDecl!=null) ConDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarConDeclList_c(\n");

        if(VarConDeclList!=null)
            buffer.append(VarConDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConDecl!=null)
            buffer.append(ConDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarConDeclList_c]");
        return buffer.toString();
    }
}
