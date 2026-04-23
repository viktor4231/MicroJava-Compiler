// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class Designator_array extends Designator {

    private DesignatorArrName DesignatorArrName;
    private Expression Expression;

    public Designator_array (DesignatorArrName DesignatorArrName, Expression Expression) {
        this.DesignatorArrName=DesignatorArrName;
        if(DesignatorArrName!=null) DesignatorArrName.setParent(this);
        this.Expression=Expression;
        if(Expression!=null) Expression.setParent(this);
    }

    public DesignatorArrName getDesignatorArrName() {
        return DesignatorArrName;
    }

    public void setDesignatorArrName(DesignatorArrName DesignatorArrName) {
        this.DesignatorArrName=DesignatorArrName;
    }

    public Expression getExpression() {
        return Expression;
    }

    public void setExpression(Expression Expression) {
        this.Expression=Expression;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorArrName!=null) DesignatorArrName.accept(visitor);
        if(Expression!=null) Expression.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrName!=null) DesignatorArrName.traverseTopDown(visitor);
        if(Expression!=null) Expression.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrName!=null) DesignatorArrName.traverseBottomUp(visitor);
        if(Expression!=null) Expression.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator_array(\n");

        if(DesignatorArrName!=null)
            buffer.append(DesignatorArrName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expression!=null)
            buffer.append(Expression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator_array]");
        return buffer.toString();
    }
}
