package com.example.rxjavapracticalblock.task_5

import io.reactivex.rxjava3.core.Single

/** Есть 2 сервера на которых лежат скидочные карты.
 * Нужно получить эти данные и вывести в единый список. (zip и тд) */


/**а) Если 1 из запросов падает, то все равно выводить (найти метод RX для такого, чтоб самому не прописывать логику)*/
fun errorResistant(
    first: Single<List<String>>,
    second: Single<List<String>>,
): Single<List<String>> = Single.zip(
        first.onErrorReturn { emptyList() },
        second.onErrorReturn { emptyList() },
    ) { f, s ->
        f + s
    }


/**б) Если 1 из запросов падает, то не выводить ничего (найти метод RX)*/
fun notErrorResistant(
    first: Single<List<String>>,
    second: Single<List<String>>,
): Single<List<String>> = Single.zip(first, second) { f, s ->
    f + s
}
