package `in`.rab.tsplex

class HistoryFragment : ItemListFragment(mCache = false, mEmptyText = R.string.no_history) {
    override fun getSigns(): List<Item> {
        val act = activity ?: return java.util.ArrayList()
        return ArrayList(SignDatabase.getInstance(act).getHistory())
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}