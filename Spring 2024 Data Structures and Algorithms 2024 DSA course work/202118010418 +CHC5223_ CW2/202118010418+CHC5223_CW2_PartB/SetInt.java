public class SetInt extends AbsSetInt {

    public SetInt(int capacity) {
        super(capacity);
    }

    /**
     * @param "x" -- value to be sought
     * @pre true
     * @return true iff x is in list*/
    @Override
    public boolean contains(int n) {
        if (getSize() == 0) {
            return false;
        }
        for (int i = 0; i < getSize(); i++) {
            if (set[i] == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param n node to be added
     * @pre contains(n) || getSize() != getCapacity()
     * @post contains(n)
     */
    @Override
    public void include(int n) {
        assert !contains(n) : "Element already exists in the set";
        assert getSize() != getCapacity() : "Set is full";
        set[size++] = n;
    }

    /**
     * @pre true
     * @post !contains(n)
     */
    @Override
    public void exclude(int n) {
        assert contains(n) : "Element does not exist in the set";
        for (int i = 0; i < getSize(); i++) {
            if (set[i] == n) {
                for (int j = i; j < getSize() - 1; j++) {
                    set[j] = set[j + 1];
                }
                size--;
                break;
            }
        }
    }
}