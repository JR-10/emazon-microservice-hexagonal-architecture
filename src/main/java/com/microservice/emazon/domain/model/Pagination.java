package com.microservice.emazon.domain.model;

import java.util.List;

public class    Pagination<T> {

    private List<T> data;
    private Long totalElements;
    private int totalPages;
    private int currentPage;
    private boolean ascending;
    private boolean empty;



    public Pagination(boolean ascending, int pageNumber, int totalPages, long totalElements, List<T> data) {
        this.ascending = ascending;
        this.currentPage = pageNumber;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.data = data;
        this.empty = data.isEmpty();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
