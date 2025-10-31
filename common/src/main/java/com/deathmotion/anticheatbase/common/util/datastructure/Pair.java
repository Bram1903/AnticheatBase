package com.deathmotion.anticheatbase.common.util.datastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Pair<X, Y> {
    private X x;
    private Y y;
}
