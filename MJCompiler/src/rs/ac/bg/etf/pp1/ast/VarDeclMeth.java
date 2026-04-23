// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class VarDeclMeth implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private VarDeclOneMeth VarDeclOneMeth;
    private VarMoreMeth VarMoreMeth;

    public VarDeclMeth (Type Type, VarDeclOneMeth VarDeclOneMeth, VarMoreMeth VarMoreMeth) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclOneMeth=VarDeclOneMeth;
        if(VarDeclOneMeth!=null) VarDeclOneMeth.setParent(this);
        this.VarMoreMeth=VarMoreMeth;
        if(VarMoreMeth!=null) VarMoreMeth.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclOneMeth getVarDeclOneMeth() {
        return VarDeclOneMeth;
    }

    public void setVarDeclOneMeth(VarDeclOneMeth VarDeclOneMeth) {
        this.VarDeclOneMeth=VarDeclOneMeth;
    }

    public VarMoreMeth getVarMoreMeth() {
        return VarMoreMeth;
    }

    public void setVarMoreMeth(VarMoreMeth VarMoreMeth) {
        this.VarMoreMeth=VarMoreMeth;
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
        if(VarDeclOneMeth!=null) VarDeclOneMeth.accept(visitor);
        if(VarMoreMeth!=null) VarMoreMeth.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclOneMeth!=null) VarDeclOneMeth.traverseTopDown(visitor);
        if(VarMoreMeth!=null) VarMoreMeth.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclOneMeth!=null) VarDeclOneMeth.traverseBottomUp(visitor);
        if(VarMoreMeth!=null) VarMoreMeth.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclMeth(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclOneMeth!=null)
            buffer.append(VarDeclOneMeth.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarMoreMeth!=null)
            buffer.append(VarMoreMeth.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclMeth]");
        return buffer.toString();
    }
}
