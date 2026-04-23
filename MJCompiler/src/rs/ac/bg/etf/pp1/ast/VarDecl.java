// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private VarDeclOne VarDeclOne;
    private VarMore VarMore;

    public VarDecl (Type Type, VarDeclOne VarDeclOne, VarMore VarMore) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclOne=VarDeclOne;
        if(VarDeclOne!=null) VarDeclOne.setParent(this);
        this.VarMore=VarMore;
        if(VarMore!=null) VarMore.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarDeclOne!=null) VarDeclOne.accept(visitor);
        if(VarMore!=null) VarMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclOne!=null) VarDeclOne.traverseTopDown(visitor);
        if(VarMore!=null) VarMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclOne!=null) VarDeclOne.traverseBottomUp(visitor);
        if(VarMore!=null) VarMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
