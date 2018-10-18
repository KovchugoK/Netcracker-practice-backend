
# Инструкция по запуску
## 1. Скачать репозиторий  
Открываем папку в которую хотим скачать проект.  
Вызываем  git bash.  
Вводим команду:  
```
git clone https://github.com/KovchugoK/Netcracker-practice-backend.git
```

1.1. (Опционально)  
Если появится новее ветка(например dev), то делаем следующее:
1. Вывод всех веток:  
```
git branch -a
```
2. Создаем и переключаемся на локальную ветку, связанную с одноименной оригинальной:  
```
git checkout dev
```

## 2. Установить PostgreSql
Я скачивал последнюю на тот момент версию 10.5 с офицального сайта  

## 3. Установить pgAdmin4
Если она не было установлена автоматически в ходе установки postgresSql  

## 4. В pgAdmin4 создать пользователя:
Запустив pgAdmin4 слева раскрываем Server->PostgreSql10.  
Там будет вкладка Login/Group Roles.  
Щелкаем ПКМ и создаем нового пользователя:  
1. В первой вкладке General вводим в поле name: user;  
2. Во 2-ой вкладке Defenition вводим в поле password: root;  
3. В третьей вкладке Priviges: везде YES.  

Юзер готов - нажимаем SAVE.  

## 5. Создаем базу
ПКМ тыкаем на вкладку Databases -> выбираем создать;  
1. Указываем имя: startup_net;  
2. Указываем owner: user.  

Все. База готова. Можно запускать приложение.
