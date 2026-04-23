// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class YesConMore extends ConMore {

    private ConDeclOne ConDeclOne;
    private ConMore ConMore;

    public YesConMore (ConDeclOne ConDeclOne, ConMore ConMore) {
        this.ConDeclOne=ConDeclOne;
        if(ConDeclOne!=null) ConDeclOne.setParent(this);
        this.ConMore=ConMore;
        if(ConMore!=null) ConMore.setParent(this);
    }

    public ConDeclOne getConDeclOne() {
        return ConDeclOne;
    }

    public void setConDeclOne(ConDeclOne ConDeclOne) {
        this.ConDeclOne=ConDeclOne;
    }

    public ConMore getConMore() {
        return ConMore;
    }

    public void setConMore(ConMore ConMore) {
        this.ConMore=ConMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConDeclOne!=null) ConDeclOne.accept(visitor);
        if(ConMore!=null) ConMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConDeclOne!=null) ConDeclOne.traverseTopDown(visitor);
        if(ConMore!=null) ConMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConDeclOne!=null) ConDeclOne.traverseBottomUp(visitor);
        if(ConMore!=null) ConMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesConMore(\n");

        if(ConDeclOne!=null)
            buffer.append(ConDeclOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConMore!=null)
            buffer.append(ConMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesConMore]");
        return buffer.toString();
    }
}
