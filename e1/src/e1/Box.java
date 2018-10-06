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
        F temp = this.getFirst();
        this.setFirst(box.getFirst());
        box.setFirst(temp);
    }

    /***
     * swapFirstWith
     */
    public void swapSecondWith(Box<?, S> box) {
        S temp = this.getSecond();
        this.setSecond(box.getSecond());
        box.setSecond(temp);
    }

    /***
     * swapItemsWith
     */
    public void swapItemsWith(Box<F, S> box) {
        this.swapFirstWith(box);
        this.swapSecondWith(box);
    }

    /***
     * moveFirstToSecond
     */
    public static <T> void moveFirstToSecond(Box<T, ? super T> box) {
        box.setSecond(box.getFirst());
        box.setFirst(null);
    }

    /***
     * swapItems
     */
    public static <T> void swapItems(Box<T, T> box) {
        T temp = box.getFirst();
        box.setFirst(box.getSecond());
        box.setSecond(temp);
    }
}
