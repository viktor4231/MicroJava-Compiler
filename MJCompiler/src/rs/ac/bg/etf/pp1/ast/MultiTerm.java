// generated with ast extension for cup
// version 0.8
// 19/7/2025 17:55:41


package rs.ac.bg.etf.pp1.ast;

public class MultiTerm extends Term {

    private Term Term;
    private MullOp MullOp;
    private Factor Factor;

    public MultiTerm (Term Term, MullOp MullOp, Factor Factor) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.MullOp=MullOp;
        if(MullOp!=null) MullOp.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public MullOp getMullOp() {
        return MullOp;
    }

    public void setMullOp(MullOp MullOp) {
        this.MullOp=MullOp;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(MullOp!=null) MullOp.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(MullOp!=null) MullOp.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(MullOp!=null) MullOp.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiTerm(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MullOp!=null)
            buffer.append(MullOp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiTerm]");
        return buffer.toString();
    }
}
