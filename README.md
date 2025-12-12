# TUBES-VERYCOOKED-OOP-IF2010
is about overcooked








````

---

### **Phase 8: Factory Pattern (Hari 13)**
````java


````

---

### **Phase 9: Testing (Hari 14)**
````java
// Test scenarios:
1. Test ingredient state transitions (RAW → CHOPPED → COOKING → COOKED → BURNED)
2. Test plating mechanism (multiple ingredients on plate)
3. Test recipe matching (order matters or not)
4. Test oven cooking timer (12s COOKED, 24s BURNED)
5. Test chef switching during busy state
6. Test plate storage (1 by 1 taking)
7. Test washing station queue
8. Test order expiration
9. Test concurrent actions (both chefs working)
10. Test collision detection
````

---

### **Phase 10: Documentation & Buklet (Hari 14-15)**
````markdown
# README.md

## Compilation
```bash
./gradlew build
```

## Running
```bash
./gradlew run
```

## Controls
- WASD: Move active chef
- C/V: Interact with station/item
- B: Switch between chefs

## Gameplay
1. Take ingredients from storage (I)
2. Chop ingredients at cutting station (C)
3. Assemble chopped ingredients on plate
4. Put plate into oven (R)
5. Wait 12 seconds for pizza to cook
6. Take out cooked pizza
7. Serve at counter (S)
8. Wash dirty plates (W)
````

---

## **TIMELINE CHECKLIST**

| Day | Tasks | Status |
|-----|-------|--------|
| 1-2 | Setup, Architecture, Enums, Interfaces | ⬜ |
| 2-4 | Item System, Ingredients, Utensils, Recipes | ⬜ |
| 4-6 | Station System (all 9 stations) | ⬜ |
| 6-8 | Chef, PlayerController | ⬜ |
| 8-9 | GameMap (Pizza layout) | ⬜ |
| 9-10 | Order System, OrderManager | ⬜ |
| 10-12 | GameManager, ScoreManager, Timers | ⬜ |
| 12-13 | CLI View & Game Loop | ⬜ |
| 13 | Factory Patterns | ⬜ |
| 14 | Testing & Bug Fixes | ⬜ |
| 14-15 | Documentation & Buklet | ⬜ |

---

## **CRITICAL NOTES FOR PIZZA MAP**

1. **Oven is NOT portable** - tetap di cooking station position
2. **Pizza workflow**: Chop all ingredients → Assemble on plate → Transfer to oven → Wait → Take out → Serve
3. **Cooking timer**: 12s → COOKED, 24s → BURNED (dari awal mulai masak)
4. **Recipe validation**: Ingredient order tidak penting, hanya name + state
5. **Plate storage**: Ambil 1 per 1 (clean atau dirty)
6. **Assembly**: Ingredient ditambahkan bertahap ke plate

Apakah ada bagian yang perlu saya detail lebih lanjut? Atau ada pertanyaan tentang implementasi tertentu?