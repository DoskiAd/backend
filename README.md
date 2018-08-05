# DoskiAD backend
Приложение - доска обьявлений.

текущее api:\
/doskiad/categories - получить список всех категорий объявлений \
/doskiad/items - получить список всех объявлений \
/doskiad/category/{ID} - получить список всех объявлений по номеру категории
/doskiad/items/{ID} - страница товара

запрос 2 и 3 поддерживают доп. параметры:\
page - номер страницы, по умолчанию 1я\
size - кол-во элементов на страницу, по умолчанию 12\
title - поиск в заголовке (не зависит от регистра)\
p - параметр сортировки (по умолчанию date)\
d - сортировка (по умолчанию desc(по убыванию))

варианты сортировки:\
id - номер объявления\
date - дата\
title - заголовок\
price - цена\
location - локация

направление сортировки:\
desc - по убыванию\
asc - по возрастанию

так же ответы на поисковые запросы содержат дополнительные заголовки:\
totalFound - общее кол-во найденых объявлений\
totalPage - общее кол-во страниц с найдеными объявлениями

пример поиска:\
/doskiad/items?title=mac&p=price&d=asc - все маки начиная с самых дешевых\
/doskiad/category/4?p=price&d=asc - все товары из категори 4 (телефоны) начиная с самых дешевых
