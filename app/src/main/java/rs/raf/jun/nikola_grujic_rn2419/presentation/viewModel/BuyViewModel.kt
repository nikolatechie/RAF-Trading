package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo

interface BuyViewModel {
    fun addAccountInfo(accountInfo: AccountInfo)
    fun getAccountInfo(username: String): AccountInfo?
}