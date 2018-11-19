#version 430
in vec4 varyingColor;
out vec4 outColor;
uniform mat4 mv_matrix;
uniform mat4 proj_matrix;

void main(void){
    outColor = varyingColor;
}