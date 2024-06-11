package com.dofuscraft;

import com.dofuscraft.entity.TelegraphEntity;

public interface SpellTelegraphAccessor {
    default TelegraphEntity dofuscraft$getSpellTelegraph() {
        return null;
    }

    default void dofuscraft$setSpellTelegraph(TelegraphEntity spellTelegraph) {
    }
}
