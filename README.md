# Rick and Morty GraphQL
Android-приложение на Jetpack Compose с использованием **GraphQL (Apollo)**, **Navigation3** и **модульной архитектуры**.  
Проект сделан как портфолио: упор на структуру кода, работу с GraphQL и навигацию с predictive back.

**API:** https://rickandmortyapi.com/graphql

---

## Содержание
- [Описание проекта](#описание-проекта)
- [Функциональность](#функциональность)
- [Архитектура и подход](#архитектура-и-подход)
- [Стек технологий](#стек-технологий)
- [Как запустить](#как-запустить)
- [Скриншоты](#скриншоты)
- [Контакты](#контакты)

---

## Описание проекта
Приложение отображает список персонажей вселенной Rick and Morty и подробную информацию о каждом из них:  
статус, расу, последнюю локацию и эпизоды, в которых персонаж появлялся.

Цель проекта — продемонстрировать:
- работу с **Apollo GraphQL** (запросы, параметры, обработка ошибок)
- **модульную структуру** приложения
- навигацию на **Navigation3** с поддержкой **predictive back**
- UI на **Jetpack Compose**

---

## Функциональность
- Список персонажей с постраничной загрузкой (**Paging 3, network-only**)
- Поиск по персонажам
- Экран персонажа (Character Info)
- Переходы между связанными персонажами
- Просмотр эпизодов и локации на экране персонажа

---

## Архитектура и подход
- Модульная структура (core + feature)
- MVI (**State / Event / Action**)
- Dependency Injection: **Hilt**
- Сетевой слой: Apollo + единая обработка ошибок через `safeQuery`

### Структура модулей

```mermaid
---
config:
  layout: dagre
  look: neo
  theme: neutral
---
flowchart LR
 subgraph APP["app"]
        App["App"]
  end
 subgraph CORE["core"]
        Core_UI["Core_UI"]
        Core_Nav["Core_Nav"]
        Core_Net["Core_Net"]
  end
 subgraph CHAR["feature_characters"]
        CHAR_PRES["Presentation"]
        CHAR_DOMAIN["Domain"]
        CHAR_DATA["Data"]
  end
 subgraph INFO["feature_character_info"]
        INFO_PRES["Presentation"]
        INFO_DOMAIN["Domain"]
        INFO_DATA["Data"]
  end
 subgraph FEATURES["features"]
        CHAR
        INFO
  end
    App --> Core_Nav & Core_UI
    Core_UI --> CHAR_PRES & INFO_PRES
    Core_Nav --> CHAR_PRES & INFO_PRES
    CHAR_PRES --> CHAR_DOMAIN
    CHAR_DOMAIN --> CHAR_DATA
    CHAR_DATA --> Core_Net
    INFO_PRES --> INFO_DOMAIN
    INFO_DOMAIN --> INFO_DATA
    INFO_DATA --> Core_Net
    CHAR_DATA --- INFO_DATA

     App:::appClass
     Core_UI:::coreUIClass
     Core_Nav:::coreNavClass
     Core_Net:::coreNetClass
     CHAR_PRES:::charClass
     CHAR_PRES:::layerClass
     CHAR_DOMAIN:::charClass
     CHAR_DOMAIN:::layerClass
     CHAR_DATA:::charClass
     CHAR_DATA:::layerClass
     INFO_PRES:::infoClass
     INFO_PRES:::layerClass
     INFO_DOMAIN:::infoClass
     INFO_DOMAIN:::layerClass
     INFO_DATA:::infoClass
     INFO_DATA:::layerClass
```

### Где смотреть ключевую логику
- Navigation3 и маршруты: `:core:navigation`
- ApolloClient / `safeQuery` / обработка ошибок: `:core:network`
- PagingSource и список персонажей: `:feature:characters`
- Детальный экран персонажа и загрузка связей: `:feature:character_info`

## Стек технологий

| Область            | Технологии                         |
|--------------------|------------------------------------|
| Язык               | Kotlin                             |
| UI                 | Jetpack Compose                    |
| Сеть               | Apollo GraphQL, OkHttp             |
| DI                 | Hilt                               |
| Навигация          | Navigation3                        |
| Пагинация          | Paging 3 (network-only)            |
| Асинхронность      | Kotlin Coroutines + Flow           |
| Картинки           | Coil                               |

## Как запустить

### Быстрая проверка (APK)
[![Download APK](https://img.shields.io/badge/Download-APK-2E2F39?style=for-the-badge&logo=android&logoColor=white)](https://github.com/muratov-dev/RickAndMortyGraphQL/releases/download/1.0.0/RaM-GraphQL-1.0.0.apk)
<p>Убедитесь, что в настройках телефона включена опция <strong>«Установка приложений из неизвестных источников»</strong>.</p>

### Запуск из Android Studio
1. Клонируйте репозиторий  
   ```bash
   git clone https://github.com/muratov-dev/RickAndMortyGraphQL
   ```
2. Откройте проект в Android Studio.
3. Запустите проект на устройстве или эмуляторе.

## Скриншоты
<div>
    <img src="im_screen_1.png" width="240" alt="Main Screen"/>
    <img src="im_screen_2.png" width="240" alt="Character Info Screen"/>
    <img src="im_screen_3.png" width="240" alt="Character Info Screen"/>
</div>

## Контакты
Email: work.yusuf.muratov@gmail.com • Telegram: @ymuratov_work
