public class Philosoph {
    private boolean eat;
    private boolean think;
    private boolean leftHand;
    private boolean rightHand = true;

    public boolean isEat() {
        return eat;
    }

    public boolean isThink() {
        return think;
    }

    public void setEat(boolean eat) {
        this.eat = eat;
    }

    public void setThink(boolean think) {
        this.think = think;
    }

    public boolean isLeftHand() {
        return leftHand;
    }

    public boolean isRightHand() {
        return rightHand;
    }

    public void setLeftHand(boolean leftHand) {
        this.leftHand = leftHand;
    }

    public void setRightHand(boolean rightHand) {
        this.rightHand = rightHand;
    }
}
