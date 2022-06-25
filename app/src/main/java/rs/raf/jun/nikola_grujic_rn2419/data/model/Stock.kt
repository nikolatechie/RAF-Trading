package rs.raf.jun.nikola_grujic_rn2419.data.model

data class Stock (
    val instrumentId: String,
    val symbol: String,
    val name: String,
    val currency: String,
    val last: Double,
    val open: Double,
    val close: Double,
    val bid: Double,
    val ask: Double,
    val metrics: StockMetrics,
    val chart: Chart
)