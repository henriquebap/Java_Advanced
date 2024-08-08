"use client";

import { useState } from 'react';
import styles from './EditProfileForm.module.css';

interface Profile {
  name: string;
  username: string;
}

const EditProfileForm: React.FC = () => {
  const [profile, setProfile] = useState<Profile>({ name: 'Email', username: '@peduarte' });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setProfile({
      ...profile,
      [name]: value,
    });
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(profile);
  };

  return (
    <div className={styles.formContainer}>
      <h2 className={styles.formTitle}>Edit profile</h2>
      <p className={styles.formDescription}>Make changes to your profile here. Click save when you're done.</p>
      <form onSubmit={handleSubmit}>
        <div className={styles.formGroup}>
          <label htmlFor="name" className={styles.formLabel}>Name</label>
          <input
            id="name"
            name="name"
            type="text"
            value={profile.name}
            onChange={handleInputChange}
            className={styles.formInput}
          />
        </div>
        <div className={styles.formGroup}>
          <label htmlFor="username" className={styles.formLabel}>Username</label>
          <input
            id="username"
            name="username"
            type="text"
            value={profile.username}
            onChange={handleInputChange}
            className={styles.formInput}
          />
        </div>
        <div className={styles.formButtonContainer}>
          <button type="submit" className={styles.formButton}>Save changes</button>
        </div>
      </form>
    </div>
  );
};

export default EditProfileForm;