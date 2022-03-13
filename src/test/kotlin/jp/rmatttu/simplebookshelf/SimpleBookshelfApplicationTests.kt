package jp.rmatttu.simplebookshelf

import jp.rmatttu.simplebookshelf.view.Pager
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SimpleBookshelfApplicationTests {

    @Test
    fun contextLoads() {
        // TODO: 考慮する
        // * null
        // * 空（""）
        // * max値
        // * 制御文字
    }

    @Test
    fun checkEmptyPagingInfo() {
        val pagerRecordEmpty = Pager(5, 0)
        var info = pagerRecordEmpty.generatePagerInfo(0)
        assert(info.totalDataCount == 0)
        assert(info.totalPageCount == 0)
        assert(info.hasNextPage.not())
        assert(info.hasPrevPage.not())
    }

    @Test
    fun checkRecordOnePagingInfo() {
        val pagerRecordOne = Pager(5, 1)
        val info = pagerRecordOne.generatePagerInfo(0)
        assert(info.totalDataCount == 1)
        assert(info.totalPageCount == 1)
        assert(info.hasNextPage.not())
        assert(info.hasPrevPage.not())
    }

    @Test
    fun checkPagingInfo() {
        val pager1 = Pager(3, 10)

        var info = pager1.generatePagerInfo(0)
        assert(info.totalDataCount == 10)
        assert(info.totalPageCount == 4) // ページは0, 1, 2, 3 の計4ページ
        assert(info.hasPrevPage.not())
        assert(info.hasNextPage)

        info = pager1.generatePagerInfo(1)
        assert(info.hasPrevPage)
        assert(info.hasNextPage)

        info = pager1.generatePagerInfo(3)
        assert(info.hasPrevPage)
        assert(info.hasNextPage.not())

        // 異常値
        info = pager1.generatePagerInfo(4)
        assert(info.hasPrevPage)
        assert(info.hasNextPage.not())
    }

}
