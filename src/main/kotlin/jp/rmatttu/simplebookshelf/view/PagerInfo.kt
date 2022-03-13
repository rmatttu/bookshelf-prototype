package jp.rmatttu.simplebookshelf.view

/**
 * ページング情報を管理します
 *
 * @param currentPageNumber 現在表示しているページ番号（０から始まります）
 * @param totalDataCount トータルのデータ件数
 * @param pageSize 1ページあたりのデータ数
 * @param totalPageCount 総ページ数
 * @param hasPrevPage 前のページ遷移が可能かどうかを表すBoolean
 * @param hasNextPage 次のページ遷移が可能かどうかを表すBoolean
 */
class PagerInfo(
    val currentPageNumber: Int,
    val totalDataCount: Int,
    val pageSize: Int,
    val totalPageCount: Int,
    val hasPrevPage: Boolean,
    val hasNextPage: Boolean
) {
    override fun toString(): String {
        return "PagerInfo(currentPageNumber=$currentPageNumber, totalDataCount=$totalDataCount, pageSize=$pageSize, totalPageCount=$totalPageCount, hasPrevPage=$hasPrevPage, hasNextPage=$hasNextPage)"
    }

}
