import EmailSchema from '@/components/EmailSchema.vue';
import failedEmail from '@/mocks/failed_email.json'

export default {
  title: 'Components/EmailSchema',
  component: EmailSchema,
  tags: ['autodocs'],
  argTypes: {
    pages: {
    }
  }
};

export const Default = {
  args: {
  }
};

export const Errors = {
  args: {
    errors: failedEmail
  }
};
