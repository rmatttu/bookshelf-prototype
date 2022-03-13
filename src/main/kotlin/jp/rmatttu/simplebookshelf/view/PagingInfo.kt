package jp.rmatttu.simplebookshelf.view

/**
 * ページング情報を管理します
 *
 * @param currentPage 現在表示しているページ番号（０から始まります）
 * @param total トータルのデータ件数
 * @param pageLength 総ページ数
 * @param hasPrevPage 前のページ遷移が可能かどうかを表すBoolean
 * @param hasNextPage 次のページ遷移が可能かどうかを表すBoolean
 */
class PagingInfo(
    val currentPage: Int,
    val total: Int,
    val pageLength: Int,
    val hasPrevPage: Boolean,
    val hasNextPage: Boolean
) {
    override fun toString(): String {
        return "PagingInfo(currentPage=$currentPage, total=$total, pageLength=$pageLength, hasPrevPage=$hasPrevPage, hasNextPage=$hasNextPage)"
    }
}
