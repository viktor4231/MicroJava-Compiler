// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class ActParsList implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ActParsOne ActParsOne;
    private ActParsMore ActParsMore;

    public ActParsList (ActParsOne ActParsOne, ActParsMore ActParsMore) {
        this.ActParsOne=ActParsOne;
        if(ActParsOne!=null) ActParsOne.setParent(this);
        this.ActParsMore=ActParsMore;
        if(ActParsMore!=null) ActParsMore.setParent(this);
    }

    public ActParsOne getActParsOne() {
        return ActParsOne;
    }

    public void setActParsOne(ActParsOne ActParsOne) {
        this.ActParsOne=ActParsOne;
    }

    public ActParsMore getActParsMore() {
        return ActParsMore;
    }

    public void setActParsMore(ActParsMore ActParsMore) {
        this.ActParsMore=ActParsMore;
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
        if(ActParsOne!=null) ActParsOne.accept(visitor);
        if(ActParsMore!=null) ActParsMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsOne!=null) ActParsOne.traverseTopDown(visitor);
        if(ActParsMore!=null) ActParsMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsOne!=null) ActParsOne.traverseBottomUp(visitor);
        if(ActParsMore!=null) ActParsMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActParsList(\n");

        if(ActParsOne!=null)
            buffer.append(ActParsOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsMore!=null)
            buffer.append(ActParsMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActParsList]");
        return buffer.toString();
    }
}
