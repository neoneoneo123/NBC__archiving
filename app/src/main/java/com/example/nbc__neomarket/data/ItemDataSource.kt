package com.example.nbc__neomarket.data

class ItemDataSource {
    companion object {
        private var INSTANCE: ItemDataSource? = null
        fun getDataSource(): ItemDataSource {
            return synchronized(ItemDataSource::class) {
                val newInstance = INSTANCE ?: ItemDataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }

    private val itemList = itemList().toMutableList<Item>()

    fun getItemList() : List<Item> {
        return itemList
    }

    fun removeItem(id: String) {
        itemList.removeAll { it.id == id }
    }
}