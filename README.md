# Overview

This is a pocket-sized, IoT-ed Power Supply.

It's final goal is to meet EEers' daily usage requirements:

- Small Sized  
- Programmable Voltage and Current settings  
- High Efficiency  
- High Reliability  
- High precision
- Multiple control method  
- Modernized GUI  

**This project is still under development and test.**

## LM5175 Parameter Configuration & Calculation

| Design Requirements | Calculated Value |
| ------------------- | ---------------- |
| Input Voltage Range | 20V ~ 24V |
| Output Range | 0.8V(Minimal) ~ 24V |
| Load Current | 0 ~ 4A(0-12V),0 ~ 2A(12-24V) |
| Switching Frequency | about 300KHz |
| Mode | CCM & Hiccup On |

### Frequency Calc

$$
R_T = {{{1 \over F_sw}-200ns} \over {37pF}}
$$

Finally get Rt = 84.5kΩ and we select standard value, that is 82kΩ.  

### Inductor Selection

$$
L_{BUCK} = {({{V_{IN(MAX)}-V_{OUT})*V_{OUT}}} \over {0.4*I_{OUT(MAX)}*F_{SW}*V_{IN(MAX)}}} 
$$

In most cases, circuit works in BUCK mode, so choose a most-common used voltage range, and calculate its L, finally we get L is about 10uH.

And the maximum average inductor current occurs at the minimum input and maximum load current:

$$
I_{L(MAX)} = {{V_{OUT}*I_{OUT(MAX)}} \over {0.9*V_{IN(MIN)}}}
$$

$$
I_{L(PEAK)} = I_{L(MAX)} + {{V_{IN(MIN)}*(V_{OUT}-V_{IN(MIN)})} \over {2*L_!*F_{SW}*V_{OUT}}}
$$

And Saturation current should be higher, about 1.5xIL, about 20A.

Finally we choose two 1265 4.7uH in series.

### What's left...
Please refer to ti's [docs](https://www.ti.com/lit/ds/symlink/lm5175.pdf?ts=1611563386531&ref_url=https%253A%252F%252Fwww.google.com%252F)



