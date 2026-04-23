// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class Statement_p1 extends Statement {

    private Expression Expression;
    private NumConst NumConst;

    public Statement_p1 (Expression Expression, NumConst NumConst) {
        this.Expression=Expression;
        if(Expression!=null) Expression.setParent(this);
        this.NumConst=NumConst;
        if(NumConst!=null) NumConst.setParent(this);
    }

    public Expression getExpression() {
        return Expression;
    }

    public void setExpression(Expression Expression) {
        this.Expression=Expression;
    }

    public NumConst getNumConst() {
        return NumConst;
    }

    public void setNumConst(NumConst NumConst) {
        this.NumConst=NumConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expression!=null) Expression.accept(visitor);
        if(NumConst!=null) NumConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expression!=null) Expression.traverseTopDown(visitor);
        if(NumConst!=null) NumConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expression!=null) Expression.traverseBottomUp(visitor);
        if(NumConst!=null) NumConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement_p1(\n");

        if(Expression!=null)
            buffer.append(Expression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NumConst!=null)
            buffer.append(NumConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement_p1]");
        return buffer.toString();
    }
}
