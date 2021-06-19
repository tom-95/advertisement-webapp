import { mount } from '@vue/test-utils'
import Dynamic from './dynamic.js';
global.axios = {
    get() { return Promise.resolve({ data: [] }) },
    post() { return Promise.resolve('value') }
}

test('shows empty list message', () => {
    const wrapper = mount(Dynamic);
    expect(wrapper.text()).toContain('You have no ads');
})

test('should clear name input', async () => {
    const wrapper = mount(Dynamic);
    const nameInput = wrapper.find('input[type="text"]');
    await nameInput.setValue('New Ad');
    await wrapper.find('button').trigger('click');
    expect(wrapper.vm.$data.nameField).toBe('');
})