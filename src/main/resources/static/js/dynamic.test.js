import { mount } from '@vue/test-utils'
import Dynamic from './dynamic.js';
global.axios = {
    get() { return Promise.resolve({ data: [] }) },
    post() { return Promise.resolve('value') }
}

test('shows "no ads" message', () => {
    const wrapper = mount(Dynamic);
    expect(wrapper.text()).toContain('You have no ads');
})