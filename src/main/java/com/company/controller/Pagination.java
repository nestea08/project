package com.company.controller;

import java.util.List;

public class Pagination<T> {
    private List<T> items;

    private int pagesCount;
    private int currentPage;
    private int itemsPerPage;

    public Pagination(List<T> items, int itemsPerPage) {
        this.items = items;
        this.itemsPerPage = itemsPerPage;
        pagesCount = calculatePagesCount();
        currentPage = 1;
    }

    private int calculatePagesCount() {
        int itemsCount = items.size();
        int pagesCount = itemsCount / itemsPerPage;
        if (isNecessaryIncompletePage(itemsCount)) {
            pagesCount++;
        }
        return pagesCount > 1 ? pagesCount : 0;
    }

    private boolean isNecessaryIncompletePage(int itemsCount) {
        return itemsCount % itemsPerPage != 0;
    }

    public List<T> getItemsForCurrentPage() {
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = startIndex + itemsPerPage;
        if (endIndex > items.size()) {
            endIndex = items.size();
        }
        return items.subList(startIndex, endIndex);
    }


    public int getPagesCount() {
        return pagesCount;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
