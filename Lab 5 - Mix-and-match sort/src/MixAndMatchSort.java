/*
* Author : Milica Knezevic, mknezevic2013@my.fit.edu
* Course : CSE 2010, Section 06, Fall 2014
* Project: lab#5, MixAndMatchSort
*/
public class MixAndMatchSort {
    Sort sort1;
    Sort sort2;
    int basecase;
    public MixAndMatchSort(Sort sort1_,Sort sort2_, int basecase_) {
        this.sort1=sort1_;
        this.sort2=sort2_;
        this.basecase=basecase_;
        }
    @SuppressWarnings("rawtypes")
    public void sort(Comparable[] list){
        this.sort(list, 0, list.length-1);
        }
    @SuppressWarnings("rawtypes")
    public void sort(Comparable[] list,int first, int last) {
        if (first<last) {
            if (last-first>this.basecase) {
                int p=this.sort1.split(list, first, last);
                this.sort(list, first, p);
                this.sort(list, p+1, last);
                this.sort1.combine(list, first, p, last);
                }else{
                    int p=this.sort2.split(list, first, last);
                    this.sort1.sort(list, first, p);
                    this.sort1.sort(list, p+1, last);
                    this.sort2.combine(list, first, p, last);
                    }
            }
        }
    }