package jp.rmatttu.simplebookshelf.view

class PagingInfo(val current: Int, val total: Int, val hasPrevPage: Boolean, val hasNextPage: Boolean) {
    override fun toString(): String {
        return "PagingInfo(current=$current, total=$total, hasPrevPage=$hasPrevPage, hasNextPage=$hasNextPage)"
    }
}
