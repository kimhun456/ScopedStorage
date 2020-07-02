package com.samsung.hyunjaee.scopedstorage

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.samsung.hyunjaee.scopedstorage.mediastore.MediaStoreFragment
import com.samsung.hyunjaee.scopedstorage.saf.SafFragment

class FragmentAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val TAB_COUNT = 2
        val TAB_TITLES = arrayOf(
            R.string.tab_media_store,
            R.string.tab_saf
        )
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            MediaStoreFragment.newInstance()
        } else {
            SafFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_COUNT
    }
}