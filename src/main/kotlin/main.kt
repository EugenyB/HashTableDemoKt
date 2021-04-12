
fun main() {
    var table = HashTable<Int>()
    table.add(1)
    table.add(10)
    table.add(11)
    table.add(4)
    table.add(5)
    table.add(7)
    println(table)

    table.add(17)
    table + 12

    println(table)
}