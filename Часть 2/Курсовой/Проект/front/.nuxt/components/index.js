export { default as DeleteProject } from '../..\\components\\DeleteProject.vue'
export { default as Form } from '../..\\components\\Form.vue'
export { default as Login } from '../..\\components\\Login.vue'
export { default as Project } from '../..\\components\\Project.vue'
export { default as ProjectForm } from '../..\\components\\ProjectForm.vue'
export { default as Projects } from '../..\\components\\Projects.vue'
export { default as Register } from '../..\\components\\Register.vue'
export { default as SelectDatesPdfDialog } from '../..\\components\\SelectDatesPdfDialog.vue'
export { default as Table } from '../..\\components\\Table.vue'
export { default as Task } from '../..\\components\\Task.vue'
export { default as TaskForm } from '../..\\components\\TaskForm.vue'
export { default as TaskList } from '../..\\components\\TaskList.vue'
export { default as WorkLogDialog } from '../..\\components\\WorkLogDialog.vue'

// nuxt/nuxt.js#8607
function wrapFunctional(options) {
  if (!options || !options.functional) {
    return options
  }

  const propKeys = Array.isArray(options.props) ? options.props : Object.keys(options.props || {})

  return {
    render(h) {
      const attrs = {}
      const props = {}

      for (const key in this.$attrs) {
        if (propKeys.includes(key)) {
          props[key] = this.$attrs[key]
        } else {
          attrs[key] = this.$attrs[key]
        }
      }

      return h(options, {
        on: this.$listeners,
        attrs,
        props,
        scopedSlots: this.$scopedSlots,
      }, this.$slots.default)
    }
  }
}
