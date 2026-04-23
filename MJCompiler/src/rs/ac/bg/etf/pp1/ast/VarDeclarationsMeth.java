// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationsMeth extends VarDeclListMeth {

    private VarDeclListMeth VarDeclListMeth;
    private VarDeclMeth VarDeclMeth;

    public VarDeclarationsMeth (VarDeclListMeth VarDeclListMeth, VarDeclMeth VarDeclMeth) {
        this.VarDeclListMeth=VarDeclListMeth;
        if(VarDeclListMeth!=null) VarDeclListMeth.setParent(this);
        this.VarDeclMeth=VarDeclMeth;
        if(VarDeclMeth!=null) VarDeclMeth.setParent(this);
    }

    public VarDeclListMeth getVarDeclListMeth() {
        return VarDeclListMeth;
    }

    public void setVarDeclListMeth(VarDeclListMeth VarDeclListMeth) {
        this.VarDeclListMeth=VarDeclListMeth;
    }

    public VarDeclMeth getVarDeclMeth() {
        return VarDeclMeth;
    }

    public void setVarDeclMeth(VarDeclMeth VarDeclMeth) {
        this.VarDeclMeth=VarDeclMeth;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclListMeth!=null) VarDeclListMeth.accept(visitor);
        if(VarDeclMeth!=null) VarDeclMeth.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclListMeth!=null) VarDeclListMeth.traverseTopDown(visitor);
        if(VarDeclMeth!=null) VarDeclMeth.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclListMeth!=null) VarDeclListMeth.traverseBottomUp(visitor);
        if(VarDeclMeth!=null) VarDeclMeth.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationsMeth(\n");

        if(VarDeclListMeth!=null)
            buffer.append(VarDeclListMeth.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclMeth!=null)
            buffer.append(VarDeclMeth.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationsMeth]");
        return buffer.toString();
    }
}
