package com.zylear.publish.web.util;

/**
 * Created by xiezongyu on 2018/8/10.
 */
public class PaginationUtil {


    public static PaginationResult paging(Integer pageRange, Integer pageIndex, Integer maxPage) {

        Integer lowPage;
        Integer topPage;
        if (pageIndex <= pageRange) {
            lowPage = 1;
            if (2 * pageRange + 1 > maxPage) {
                topPage = maxPage;
            } else {
                topPage = 2 * pageRange + 1;
            }
        } else if (pageIndex + pageRange > maxPage) {

            if (maxPage - 2 * pageRange - 1 > 0) {
                lowPage = maxPage - 2 * pageRange;
            } else {
                lowPage = 1;
            }
            topPage = maxPage;
        } else {
            if (pageIndex - pageRange > 0) {
                lowPage = pageIndex - pageRange;
            } else {
                lowPage = 1;
            }
            if (pageIndex + pageRange > maxPage) {
                topPage = maxPage;
            } else {
                topPage = pageIndex + pageRange;
            }
        }
        return new PaginationResult(lowPage, topPage);
    }


    public static class PaginationResult {

        public PaginationResult() {
        }

        public PaginationResult(Integer lowPage, Integer topPage) {
            this.lowPage = lowPage;
            this.topPage = topPage;
        }

        private Integer lowPage;
        private Integer topPage;

        public Integer getLowPage() {
            return lowPage;
        }

        public void setLowPage(Integer lowPage) {
            this.lowPage = lowPage;
        }

        public Integer getTopPage() {
            return topPage;
        }

        public void setTopPage(Integer topPage) {
            this.topPage = topPage;
        }
    }


}
