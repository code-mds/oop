package e1;

public class Box<F,S> {
    private F first;
    private S second;

    public Box(F f, S s) {
        this.first = f;
        this.second = s;
    }

    @Override
    public String toString() {
        return String.format("Box{first=%s, second=%s}", first,second);
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    private void checkIsNull(Object o) {
        if(o != null)
            throw new BoxRuntimeException();
    }

    /***
     * moveFirstTo
     */
    public void moveFirstTo(Box<? super F, ?> to) {
        checkIsNull(to.getFirst());
        to.setFirst(this.getFirst());
        this.setFirst(null);
    }

    /***
     * moveSecondTo
     */
    public void moveSecondTo(Box<?, ? super S> to) {
        checkIsNull(to.getSecond());
        to.setSecond(this.getSecond());
        this.setSecond(null);
    }

    /***
     * moveItemsFrom
     */
    public void moveItemsFrom(Box<? extends F, ? extends S> from) {
        from.moveFirstTo(this);
        from.moveSecondTo(this);
    }

    /***
     * swapFirstWith
     */
    public void swapFirstWith(Box<F, ?> box) {
        F tempFirst = this.getFirst();
        this.setFirst(box.getFirst());
        box.setFirst(tempFirst);
    }

    /***
     * moveFirstToSecond
     */
    public static <T> void moveFirstToSecond(Box<T, ? super T> to) {
        to.setSecond(to.getFirst());
        to.setFirst(null);
    }

//    swapItemsWith
//    swapItems

}
