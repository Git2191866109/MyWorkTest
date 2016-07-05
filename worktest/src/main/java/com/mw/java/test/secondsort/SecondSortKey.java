package com.mw.java.test.secondsort;

import scala.Serializable;
import scala.math.Ordered;

/**
 * Created by mawei on 2016/7/5.
 */
public class SecondSortKey implements Ordered<SecondSortKey>, Serializable {
    /*需要二次排序的key*/
    private Integer firstKey;
    private Integer secondKey;

    public Integer getFirstKey() {
        return firstKey;
    }

    public void setFirstKey(Integer firstKey) {
        this.firstKey = firstKey;
    }

    public Integer getSecondKey() {
        return secondKey;
    }

    public void setSecondKey(Integer secondKey) {
        this.secondKey = secondKey;
    }

    public SecondSortKey(Integer firstKey, Integer secondKey) {
        this.firstKey = firstKey;
        this.secondKey = secondKey;
    }

    @Override
    public boolean $less(SecondSortKey that) {
        if (this.firstKey < that.getFirstKey()) {
            return true;
        } else if (this.firstKey == that.getFirstKey() && this.secondKey < that.getSecondKey()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $greater(SecondSortKey that) {
        if (this.firstKey > that.getFirstKey()) {
            return true;
        } else if (this.firstKey == that.getFirstKey() && this.secondKey > that.getSecondKey()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $less$eq(SecondSortKey that) {
        if (this.$less(that)) {
            return true;
        } else if (this.firstKey == that.getFirstKey() && this.secondKey == that.getSecondKey()) {
            return true;
        }
        return false;

    }

    @Override
    public boolean $greater$eq(SecondSortKey that) {
        if (this.$greater(that)) {
            return true;
        } else if (this.firstKey == that.getFirstKey() && this.secondKey == that.getSecondKey()) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(SecondSortKey that) {
        if (this.firstKey - that.getFirstKey() != 0) {
            return this.firstKey - that.getFirstKey();
        } else {
            return this.secondKey - that.getSecondKey();
        }
    }

    @Override
    public int compare(SecondSortKey that) {
        if (this.firstKey - that.getFirstKey() != 0) {
            return this.firstKey - that.getFirstKey();
        } else {
            return this.secondKey - that.getSecondKey();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecondSortKey that = (SecondSortKey) o;

        if (firstKey != null ? !firstKey.equals(that.firstKey) : that.firstKey != null) return false;
        return secondKey != null ? secondKey.equals(that.secondKey) : that.secondKey == null;

    }

    @Override
    public int hashCode() {
        int result = firstKey != null ? firstKey.hashCode() : 0;
        result = 31 * result + (secondKey != null ? secondKey.hashCode() : 0);
        return result;
    }
}
