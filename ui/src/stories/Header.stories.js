import Header from '@/components/Header.vue';

export default {
  title: 'Components/Header',
  component: Header,
  tags: ['autodocs'],
  argTypes: {
    pages: {
    }
  }
};

export const Default = {
  args: {
    pages: {"Hjem": "/", "Nyheter": "/nyheter", "Kontakt": "/kontakt"}
  }
};

export const Admin = {
  args: {
    pages: {"Hjem": "/", "Nyheter": "/nyheter", "Kontakt": "/kontakt", "Admin": "/admin"}
  }
};