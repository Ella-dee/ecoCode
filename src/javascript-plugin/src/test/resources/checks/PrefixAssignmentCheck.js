let str = '';

for (let i = 0; i < 9; i++) {  // Noncompliant {{Refactor the code to avoid creating provisional variable: ++$i / $i++}}
  str = str + i;
}

var a = 1;
console.log(a++); // Noncompliant {{Refactor the code to avoid creating provisional variable: ++$i / $i++}}

console.log(--a);

console.log(str);
