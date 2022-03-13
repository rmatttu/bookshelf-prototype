package jp.rmatttu.simplebookshelf.view

import kotlin.math.ceil

/**
 * ページ境界を管理します
 *
 * @param pageSize 1ページあたりのデータ数
 * @param totalDataCount トータルデータ量
 * @property totalPageCount 総ページ数
 */
class Pager(val pageSize: Int, val totalDataCount: Int) {
    val totalPageCount = ceil(totalDataCount / pageSize.toDouble()).toInt()

    /**
     * 現在のページ（**0から始まります++）を指定して、前のページに遷移できるかどうかを返します
     *
     * @param 0から始まるページ番号
     */
    private fun hasPrevPage(pageNumber: Int): Boolean {
        return 0 < pageNumber
    }

    /**
     * 現在のページ（**0から始まります++）を指定して、次のページに遷移できるかどうかを返します
     *
     * @param 0から始まるページ番号
     */
    private fun hasNextPage(pageNumber: Int): Boolean {
        return (pageNumber + 1) < totalPageCount
    }

    /**
     * ページング情報を生成します
     *
     * @param 0から始まるページ番号
     */
    fun generatePagerInfo(pageNumber: Int): PagerInfo {
        return PagerInfo(
            pageNumber,
            totalDataCount,
            pageSize,
            totalPageCount,
            hasPrevPage(pageNumber),
            hasNextPage(pageNumber)
        )
    }

    override fun toString(): String {
        return "Pager(pageSize=$pageSize, totalCount=$totalDataCount, pageLength=$totalPageCount)"
    }

}
